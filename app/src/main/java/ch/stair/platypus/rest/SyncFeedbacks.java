package ch.stair.platypus.rest;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;

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

    public void fetchFeedbacksBefore(final long time, final Observer<List<FeedbackModel>> observer) {
        Call<List<FeedbackPOJO>> call = client.getFeedback(time);
        call.enqueue(new Callback<List<FeedbackPOJO>>() {
            @Override
            public void onResponse(Call<List<FeedbackPOJO>> call, Response<List<FeedbackPOJO>> response) {
                if (response.isSuccessful() && !response.body().isEmpty()) {
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
}
