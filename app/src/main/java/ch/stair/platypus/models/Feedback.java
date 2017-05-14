package ch.stair.platypus.models;

import java.util.Date;
import java.util.List;

import io.objectbox.annotation.Backlink;
import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;
import io.objectbox.annotation.Relation;
import io.objectbox.relation.ToMany;
import io.objectbox.annotation.Generated;
import io.objectbox.annotation.apihint.Internal;
import io.objectbox.BoxStore;
import io.objectbox.relation.ToOne;
import io.objectbox.Box;

@Entity
public class Feedback {
    @Id(assignable = true)
    private long id;

    private String feedbackText;
    private Integer parentId;
    private Date lastModified;
    private Integer votesCount;

    @Backlink
    List<FeedbackHashtag> feedbackHashtagses = new ToMany<>(this, Feedback_.feedbackHashtagses);

    //Reference to self
    long feedbackId;
    @Relation(idProperty = "feedbackId")
    Feedback parent;

    @Backlink
    List<Feedback> children = new ToMany<>(this, Feedback_.children);

    /** Used to resolve relations */
    @Internal
    @Generated(1307364262)
    transient BoxStore __boxStore;

    @Internal
    @Generated(1332578580)
    transient ToOne<Feedback> parentToOne = new ToOne<>(this, Feedback_.parent);

    @Generated(1385729640)
    @Internal
    /** This constructor was generated by ObjectBox and may change any time. */
    public Feedback(long id, String feedbackText, Integer parentId, Date lastModified, Integer votesCount,
            long feedbackId) {
        this.id = id;
        this.feedbackText = feedbackText;
        this.parentId = parentId;
        this.lastModified = lastModified;
        this.votesCount = votesCount;
        this.feedbackId = feedbackId;
    }
    @Generated(802671868)
    public Feedback() {
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
    /** To-one relationship, resolved on first access. */
    @Generated(1469121773)
    public Feedback getParent() {
        parent = parentToOne.getTarget(this.feedbackId);
        return parent;
    }
    /** Set the to-one relation including its ID property. */
    @Generated(789168430)
    public void setParent(Feedback parent) {
        parentToOne.setTarget(parent);
        this.parent = parent;
    }
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
}
