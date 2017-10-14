package ch.stair.platypus.rest;

import android.util.Log;

import java.util.List;
import java.util.stream.Stream;

import javax.inject.Inject;

import ch.stair.platypus.PreferencesManager;
import ch.stair.platypus.repository.models.Feedback;
import ch.stair.platypus.repository.models.FeedbackHashtag;
import ch.stair.platypus.repository.models.Hashtag;
import ch.stair.platypus.rest.model.FeedbackPOJO;
import ch.stair.platypus.rest.model.HashtagPOJO;
import io.objectbox.Box;
import io.objectbox.BoxStore;
import io.objectbox.exception.DbException;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static ch.stair.platypus.mapper.FeedbackMapper.mapFeedbackPOJOToFeedback;
import static ch.stair.platypus.mapper.HashtagMapper.mapHashtagPOJOToHashtag;

public class SyncFeedbacks {

    private final PlatypusClient client;
    private final  Box<Feedback> feedbackBox;
    private final Box<Hashtag> hashtagBox;

    @Inject
    public SyncFeedbacks(final BoxStore boxStore) {
        this.feedbackBox = boxStore.boxFor(Feedback.class);
        this.hashtagBox = boxStore.boxFor(Hashtag.class);

        this.client = ServiceGenerator.createService(PlatypusClient.class);
    }

    public void fetchLatestFeedbacksToDB() {
        Call<List<FeedbackPOJO>> call = client.getFeedback(getLastSyncDate());

        call.enqueue(new Callback<List<FeedbackPOJO>>() {

            @Override
            public void onResponse(Call<List<FeedbackPOJO>> call, Response<List<FeedbackPOJO>> response) {
                if (response.isSuccessful()) {
                    // tasks available
                    Log.d("Debug", "Response is Successful");
                    if (!response.body().isEmpty()) {
                        setLastSyncDate();
                        saveNewFeedbacksToDB(response.body());
                    }

                } else {
                    // error response, no access to resource?
                    Log.d("Error", "Error in Response");
                }
            }

            @Override
            public void onFailure(Call<List<FeedbackPOJO>> call, Throwable t) {
                Log.d("Error", t.getMessage());
            }
        });
    }

    private void saveNewFeedbacksToDB(List<FeedbackPOJO> newFeedbackPOJOs) {
        try {
            Stream<HashtagPOJO> allHashtagPOJOs = newFeedbackPOJOs.stream().flatMap(f -> f.getHashtags().stream()).distinct();
            allHashtagPOJOs.forEach(hashtagPOJO -> hashtagBox.put(mapHashtagPOJOToHashtag(hashtagPOJO)));

            for (FeedbackPOJO updatedFeedbackPOJO : newFeedbackPOJOs) {
                Feedback updatedFeedback = mapFeedbackPOJOToFeedback(updatedFeedbackPOJO);

                feedbackBox.put(updatedFeedback);

                if(updatedFeedback.feedbackHashtagses != null && updatedFeedback.feedbackHashtagses.size() > 0){
                    updatedFeedback.feedbackHashtagses.clear();

                }



                for (HashtagPOJO hashtagPOJO : updatedFeedbackPOJO.getHashtags()) {
                    updatedFeedback.feedbackHashtagses.add(new FeedbackHashtag(0, updatedFeedback.getId(), hashtagPOJO.getId()));
                }
                feedbackBox.put(updatedFeedback);
            }
        } catch (DbException e) {
            Log.d("Error", "Feedback ID not unique: " + e.getMessage());
        } catch (Exception ex) {
            Log.d("Error", ex.getMessage());
        }
    }

    private long getLastSyncDate() {
        final PreferencesManager prefManager = PreferencesManager.getInstance();
        return prefManager.getLongValue(PreferencesManager.KEYS.LAST_SYNC);
    }

    private void setLastSyncDate() {
        final PreferencesManager prefManager = PreferencesManager.getInstance();
        final int FiveMinutesServerAndClientTimeDifferenceBuffer = 5 * 60;
        final long lastSync = (System.currentTimeMillis() / 1000L) - FiveMinutesServerAndClientTimeDifferenceBuffer;
        prefManager.setValue(PreferencesManager.KEYS.LAST_SYNC, lastSync);
    }
}
