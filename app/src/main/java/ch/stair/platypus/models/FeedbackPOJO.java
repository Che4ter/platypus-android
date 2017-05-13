package ch.stair.platypus.models;

import java.util.Date;
import java.util.List;

public class FeedbackPOJO {
    private Integer id;
    private String feedbackText;
    private Date lastModified;
    private Integer votes;
    private List<HashtagPOJO> hashtags;
    private String userName;

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

    public Date getLastModified() {
        return lastModified;
    }

    public void setLastModified(Date lastModified) {
        this.lastModified = lastModified;
    }

    public Integer getVotes() {
        return votes;
    }

    public void setVotes(Integer votes) {
        this.votes = votes;
    }

    public List<HashtagPOJO> getHashtags() {
        return hashtags;
    }

    public void setHashtags(List<HashtagPOJO> hashtags) {
        this.hashtags = hashtags;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
