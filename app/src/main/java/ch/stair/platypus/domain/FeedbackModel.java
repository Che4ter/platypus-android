package ch.stair.platypus.domain;

import java.util.Date;

public class FeedbackModel {
    private final long id;
    private final Date createdOn;
    private final String text;
    private final int voteCount;

    public FeedbackModel(
            final long id,
            final String text,
            final Date createdOn,
            final int voteCount) {
        this.id = id;
        this.text = text;
        this.createdOn = createdOn;
        this.voteCount = voteCount;
    }

    public long getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public int getVoteCount() {
        return voteCount;
    }
}
