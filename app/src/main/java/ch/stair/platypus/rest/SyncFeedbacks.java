package ch.stair.platypus.rest;

import android.util.Log;

import java.util.List;
import java.util.stream.Stream;

import ch.stair.platypus.App;
import ch.stair.platypus.PreferencesManager;
import ch.stair.platypus.models.Feedback;
import ch.stair.platypus.models.FeedbackHashtag;
import ch.stair.platypus.models.FeedbackPOJO;
import ch.stair.platypus.models.Hashtag;
import ch.stair.platypus.models.HashtagPOJO;
import io.objectbox.Box;
import io.objectbox.exception.DbException;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static ch.stair.platypus.mapper.FeedbackMapper.mapFeedbackPOJOToFeedback;
import static ch.stair.platypus.mapper.HashtagMapper.mapHashtagPOJOToHashtag;

public class SyncFeedbacks {

    final private PlatypusClient client;
    final private Box<Feedback> feedbackBox;
    final private Box<Hashtag> hashtagBox;

    public SyncFeedbacks(App applicationContext) {

        feedbackBox = applicationContext
                .getBoxStore()
                .boxFor(Feedback.class);

        hashtagBox = applicationContext
                .getBoxStore()
                .boxFor(Hashtag.class);

        client = ServiceGenerator.createService(PlatypusClient.class);
    }

    public void fetchLatestFeedbacksToDB() {
        Call<List<FeedbackPOJO>> call = client.getFeedback(getLastSyncDate());
        call.enqueue(new Callback<List<FeedbackPOJO>>() {

            @Override
            public void onResponse(Call<List<FeedbackPOJO>> call, Response<List<FeedbackPOJO>> response) {
                if (response.isSuccessful()) {
                    setLastSyncDate();
                    // tasks available
                    Log.d("Debug", "Response is Successful");
                    if (!response.body().isEmpty()) {
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

                updatedFeedback.feedbackHashtagses.clear();

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

    private long getLastSyncDate()
    {
        PreferencesManager prefManager = PreferencesManager.getInstance();
        return prefManager.getLongValue(PreferencesManager.KEYS.LAST_SYNC);
    }

    private void setLastSyncDate()
    {
        PreferencesManager prefManager = PreferencesManager.getInstance();
        long newSyncDate = (System.currentTimeMillis() / 1000L) - (5L * 60L); //Substract 5min for time differences between server and client
        prefManager.setValue(PreferencesManager.KEYS.LAST_SYNC,newSyncDate);
    }
}
