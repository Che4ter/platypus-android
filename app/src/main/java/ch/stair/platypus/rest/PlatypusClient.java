package ch.stair.platypus.rest;

import java.util.List;

import ch.stair.platypus.models.FeedbackPOJO;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

interface PlatypusClient {
    @GET("feedback")
    Call<List<FeedbackPOJO>> getFeedback(@Query("lastsync") long date);
}
