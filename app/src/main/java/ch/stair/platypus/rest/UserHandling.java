package ch.stair.platypus.rest;

import android.util.Log;

import com.google.gson.JsonObject;

import ch.stair.platypus.rest.model.RegistrationLoginPOJO;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserHandling{

    final private PlatypusClient client;

    public UserHandling() {
        client = ServiceGenerator.createService(PlatypusClient.class);
    }

    /**
    *@deprecated this is only for development, use the Platyus Authenticator
    **/
    @Deprecated
    public void createNewUserASYNC(String emailAddress, String password) {
        Call<JsonObject> call = client.registerUser(new RegistrationLoginPOJO(emailAddress, password));
        call.enqueue(new Callback<JsonObject>() {

            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if (response.isSuccessful()) {
                    Log.d("Debug", "Response is Successful");
                    if (response.body().get("success").getAsInt() == 1) {
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

    /**
     *@deprecated this is only for development, use the Platyus Authenticator
     **/
    @Deprecated
    public void loginUserASYNC(String emailAddress, String password) {
        Call<JsonObject> call = client.loginUser(new RegistrationLoginPOJO(emailAddress, password));
        call.enqueue(new Callback<JsonObject>() {

            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if (response.isSuccessful()) {
                    Log.d("Debug", "Response is Successful");
                    if (response.body().get("success").getAsInt() == 1) {
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

    public String loginUserBLOCKING(String email, String pass) {
        String token = null;
        Call<JsonObject> call = client.loginUser(new RegistrationLoginPOJO(email, pass));
        try {
            JsonObject result = call.execute().body();

            if (result.get("success").getAsInt() == 1) {
                token = result.get("token").getAsString();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return token;
    }

    public boolean createNewUserBlocking(String email, String pass) {
        boolean successfull = false;
        Call<JsonObject> call = client.registerUser(new RegistrationLoginPOJO(email, pass));
        try {
            JsonObject result = call.execute().body();
           if (result.get("success").getAsInt() == 1) {
                successfull = true;
                Log.d("Debug", "User created");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return successfull;
    }
}
