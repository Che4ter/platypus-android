package ch.stair.platypus.rest;

import ch.stair.platypus.models.FeedbackPOJO;
import retrofit2.Call;
import retrofit2.http.GET;

public interface PlatypusClient {
    @GET("/feedback")
    Call<FeedbackPOJO> getFeedback();
}
