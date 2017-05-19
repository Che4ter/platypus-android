package ch.stair.platypus.rest;

import com.google.gson.JsonObject;

import java.util.List;

import ch.stair.platypus.rest.model.FeedbackPOJO;
import ch.stair.platypus.rest.model.RegistrationLoginPOJO;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

interface PlatypusClient {
    @GET("feedback")
    Call<List<FeedbackPOJO>> getFeedback(
            @Query("lastsync") long date
    );

    @POST("user")
    Call<JsonObject> registerUser(@Body RegistrationLoginPOJO userCreationPOJO);

    @POST("auth/token")
    Call<JsonObject> loginUser(@Body RegistrationLoginPOJO userCreationPOJO);
}
