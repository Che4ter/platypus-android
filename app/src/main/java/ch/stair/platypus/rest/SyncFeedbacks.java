package ch.stair.platypus.rest;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;

import ch.stair.platypus.PreferencesManager;
import ch.stair.platypus.domain.FeedbackModel;
import ch.stair.platypus.domain.Observer;
import ch.stair.platypus.rest.model.FeedbackPOJO;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SyncFeedbacks {

    private final PlatypusClient client;

    @Inject
    public SyncFeedbacks() {
        this.client = ServiceGenerator.createService(PlatypusClient.class);
    }

    public void fetchLatestFeedbacks(Observer<List<FeedbackModel>> observer) {
        Call<List<FeedbackPOJO>> call = client.getFeedback(getLastSyncDate());
        call.enqueue(new Callback<List<FeedbackPOJO>>() {
            @Override
            public void onResponse(Call<List<FeedbackPOJO>> call, Response<List<FeedbackPOJO>> response) {
                if (response.isSuccessful() && !response.body().isEmpty()) {
                    setLastSyncDate();
                    final List<FeedbackModel> feedbacks = mapToFeedbackModel(response);
                    observer.onFinished(feedbacks);
                }
            }

            @Override
            public void onFailure(Call<List<FeedbackPOJO>> call, Throwable t) {
                Log.d("Error", t.getMessage());
            }
        });
    }

    private List<FeedbackModel> mapToFeedbackModel(Response<List<FeedbackPOJO>> response) {
        return response
            .body()
            .stream()
            .map(
                x -> new FeedbackModel(
                    x.getId(),
                    x.getFeedbackText(),
                    x.getCreationDate(),
                    x.getVotesCount(),
                    new ArrayList<>())
            ).collect(Collectors.toList());
    }

    private long getLastSyncDate() {
        final PreferencesManager prefManager = PreferencesManager.getInstance();
        final long lastSync = prefManager.getLongValue(PreferencesManager.KEYS.LAST_SYNC);
        return lastSync;
    }

    private void setLastSyncDate() {
        final PreferencesManager prefManager = PreferencesManager.getInstance();
        final int FiveMinutesServerAndClientTimeDifferenceBuffer = 5 * 60;
        final long lastSync = (System.currentTimeMillis() / 1000L) - FiveMinutesServerAndClientTimeDifferenceBuffer;
        prefManager.setValue(PreferencesManager.KEYS.LAST_SYNC, lastSync);
    }
}
