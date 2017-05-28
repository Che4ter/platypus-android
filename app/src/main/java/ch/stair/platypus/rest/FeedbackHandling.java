package ch.stair.platypus.rest;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.accounts.AuthenticatorException;
import android.accounts.OperationCanceledException;
import android.content.Context;
import android.util.Log;

import com.google.gson.JsonObject;

import java.io.IOException;

import javax.inject.Inject;

import ch.stair.platypus.authentication.AccountGeneral;
import ch.stair.platypus.domain.CreateFeedbackModel;
import ch.stair.platypus.rest.model.FeedbackCreationPOJO;
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

    public void voteOnFeedback(long feedbackId, Integer direction, Context context) throws AuthenticatorException, OperationCanceledException, IOException {
        AccountManager tmp = AccountManager.get(context);

        final Account[] accounts = tmp.getAccountsByType(AccountGeneral.ACCOUNT_TYPE);
        if (accounts.length > 0) {

            final boolean doNotNotifyAuthFailure = false;
            String token = tmp.blockingGetAuthToken(
                    accounts[0],
                    AccountGeneral.AUTHTOKEN_TYPE_STUDENT_ACCESS,
                    doNotNotifyAuthFailure);
            VotePOJO newVote = new VotePOJO(direction);
            String jwt_token = "Bearer " + token;
            Call<JsonObject> call = client.voteOnComment(feedbackId, jwt_token, newVote);
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
                        throw new IllegalArgumentException();

                    }
                }

                @Override
                public void onFailure(Call<JsonObject> call, Throwable t) {
                    Log.d("Error", t.getMessage());
                    throw new IllegalArgumentException();

                }
            });
        }
    }

    public void createFeedback(final CreateFeedbackModel createFeedbackModel,final String token) {
        FeedbackCreationPOJO newFeedback = new FeedbackCreationPOJO(createFeedbackModel.getText(),0,null);

        String jwt_token = "Bearer " + token;
        Call<JsonObject> call = client.createFeedback(jwt_token,newFeedback);
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
                    try {
                        String a = response.errorBody().string();
                        Log.d("Error", "Error in Response: " + a);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                Log.d("Error", t.getMessage());
            }
        });
    }

}
