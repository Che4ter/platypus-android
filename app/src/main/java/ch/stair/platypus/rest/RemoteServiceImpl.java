package ch.stair.platypus.rest;

import android.accounts.AuthenticatorException;
import android.accounts.OperationCanceledException;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;

import ch.stair.platypus.authentication.AuthenticationManager;
import ch.stair.platypus.domain.CreateFeedbackModel;
import ch.stair.platypus.domain.FeedbackModel;
import ch.stair.platypus.domain.Observer;
import ch.stair.platypus.domain.RemoteService;
import ch.stair.platypus.rest.model.FeedbackPOJO;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RemoteServiceImpl implements RemoteService{

    private final AuthenticationManager authenticationManager;
    private final PlatypusClient client;

    @Inject
    public RemoteServiceImpl(final AuthenticationManager authenticationManager){
        this.authenticationManager = authenticationManager;
        this.client = ServiceGenerator.createService(PlatypusClient.class);
    }

    @Override
    public boolean createFeedback(final CreateFeedbackModel createFeedbackModel) {
        boolean wasSuccessful = false;

        try {
            final String token = this.authenticationManager.getToken();
            if (token != null) {
                FeedbackHandling newFeedback = new FeedbackHandling();
                newFeedback.createFeedback(createFeedbackModel, token);
                wasSuccessful = true;
            }
        } catch (AuthenticatorException | OperationCanceledException | IOException e) {
            e.printStackTrace();
        }

        return wasSuccessful;
    }

    @Override
    public void fetchFeedbacksBefore(Date date, Observer<List<FeedbackModel>> observer) {
        Call<List<FeedbackPOJO>> call = client.getFeedback(date.getTime());
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
