package ch.stair.platypus.rest.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FeedbackCreationPOJO {
    @SerializedName("feedback_text")
    private String feedbackText;

    @SerializedName("parent_id")
    private Integer parentId;

    @SerializedName("hashtags")
    private List<HashtagPOJO> hashtags;

    public FeedbackCreationPOJO(String feedbackText, Integer parentId, List<HashtagPOJO> hashtags){
        this.feedbackText = feedbackText;
        this.parentId = parentId;
        this.hashtags = hashtags;
    }

}
