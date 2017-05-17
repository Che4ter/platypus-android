package ch.stair.platypus.rest;

import android.util.Log;

import com.google.gson.JsonObject;

import ch.stair.platypus.models.RegistrationLoginPOJO;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserHandling {

    final private PlatypusClient client;

    public UserHandling() {
        client = ServiceGenerator.createService(PlatypusClient.class);
    }

    public void registerNewUser(String emailAddress, String password) {
        Call<JsonObject> call = client.registerUser(new RegistrationLoginPOJO(emailAddress,password));
        call.enqueue(new Callback<JsonObject>() {

            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if (response.isSuccessful()) {
                    Log.d("Debug", "Response is Successful");
                    if(response.body().get("success").getAsInt() == 1){
                        Log.d("Debug", "User created");

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

    public void loginUser(String emailAddress, String password) {
        Call<JsonObject> call = client.loginUser(new RegistrationLoginPOJO(emailAddress,password));
        call.enqueue(new Callback<JsonObject>() {

            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if (response.isSuccessful()) {
                    Log.d("Debug", "Response is Successful");
                    if(response.body().get("success").getAsInt() == 1){
                        String token = response.body().get("token").getAsString();
                        Log.d("Debug", "User logged in");

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
