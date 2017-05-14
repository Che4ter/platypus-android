package ch.stair.platypus.models;

import java.util.Date;
import java.util.List;

public class FeedbackPOJO {
    private Integer id;
    private String feedbackText;
    private Integer parentId;
    private Date lastModified;
    private Integer votesCount;
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

    public Integer parentId() {
        return id;
    }

    public void parentId(Integer id) {
        this.id = id;
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
