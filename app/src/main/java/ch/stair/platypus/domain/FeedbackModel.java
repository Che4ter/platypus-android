package ch.stair.platypus.domain;

import java.util.Date;
import java.util.List;

import ch.stair.platypus.repository.models.FeedbackHashtag;
import ch.stair.platypus.repository.models.Hashtag;

public class FeedbackModel {
    private final long id;
    private final Date createdOn;
    private final String text;
    private int voteCount;
    private final String hashtags;

    public FeedbackModel(
            final long id,
            final String text,
            final Date createdOn,
            final int voteCount,
            final List<FeedbackHashtag> feedbackHashtag) {
        this.id = id;
        this.text = text;
        this.createdOn = createdOn;
        this.voteCount = voteCount;
        StringBuilder tmp = new StringBuilder();
        for(FeedbackHashtag fHashtag : feedbackHashtag) {
                Hashtag a = fHashtag.getHashtag();
                if(a != null)
                {
                    tmp.append("#" + a.getHashText());
                }
                //ugly quickfix for school live demo.
                //todo: make hashtag display complete new
                break;
        }
        this.hashtags = tmp.toString();
    }

    public long getId() {
        return this.id;
    }

    public String getText() {
        return this.text;
    }

    public Date getCreatedOn() {
        return this.createdOn;
    }

    public int getVoteCount() {
        return this.voteCount;
    }

    public String getHashtags() {return this.hashtags;}

    public void upVote(){
        this.voteCount++;
    }

    public void downVote(){
        this.voteCount--;
    }
}
