package ch.stair.platypus.models;

import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.List;

public class FeedbackPOJO {

    @SerializedName("id")
    private Integer id;

    @SerializedName("feedback_text")
    private String feedbackText;

    @SerializedName("parent_id")
    private Integer parentId;

    @SerializedName("last_modified")
    private Date lastModified;

    @SerializedName("votes_count")
    private Integer votesCount;

    @SerializedName("hashtags")
    private List<HashtagPOJO> hashtags;

    public FeedbackPOJO()
    {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFeedbackText() {
        return feedbackText;
    }

    public void setFeedbackText(String feedbackText) {
        this.feedbackText = feedbackText;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Date getLastModified() {
        return lastModified;
    }

    public void setLastModified(Date lastModified) {
        this.lastModified = lastModified;
    }

    public Integer getVotesCount() {
        return votesCount;
    }

    public void setVotesCount(Integer votesCount) {
        this.votesCount = votesCount;
    }

    public List<HashtagPOJO> getHashtags() {
        return hashtags;
    }

    public void setHashtags(List<HashtagPOJO> hashtags) {
        this.hashtags = hashtags;
    }
}
