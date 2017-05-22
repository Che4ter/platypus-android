package ch.stair.platypus.rest;

import android.util.Log;

import com.google.gson.JsonObject;

import javax.inject.Inject;

import ch.stair.platypus.rest.model.VotePOJO;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FeedbackHandling {

    private final PlatypusClient client;

    @Inject
    public FeedbackHandling() {
          this.client = ServiceGenerator.createService(PlatypusClient.class);
    }

    public void voteOnFeedback(long feedbackId, Integer direction) {
        VotePOJO newVote = new VotePOJO(direction);
        String jwt_token = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE0OTU0NjY2MTUsImV4cCI6MTUwMTQ2NjYxNSwianRpIjoiM0FFRklvQkY2WmZzcDVmcnc1OStNUT09Iiwic3ViIjoyNywicm9sZV9pZCI6MX0.T55XtNboYDf2C25VYX01rOTJPM8vScq6tk0-qopawzc";
        Call<JsonObject> call = client.voteOnComment(feedbackId,jwt_token,newVote);
        call.enqueue(new Callback<JsonObject>() {

            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if (response.isSuccessful()) {
                    // tasks available
                    Log.d("Debug", "Response is Successful");
                    if (response.body().toString() == "") {
                    }

                } else {
                    // error response, no access to resource?
                    Log.d("Error", "Error in Response");
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                Log.d("Error", t.getMessage());
            }
        });
    }

}
