package ch.stair.platypus.models;

import java.util.Date;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Generated;
import io.objectbox.annotation.Id;
import io.objectbox.annotation.apihint.Internal;

@Entity
public class Comments {
    @Id
    private long id;

    private String comment;
    private Date created;
    private int voteCount;

    @Generated(1028469617)
    @Internal
    /** This constructor was generated by ObjectBox and may change any time. */
    public Comments(long id, String comment, Date created, int voteCount) {
        this.id = id;
        this.comment = comment;
        this.created = created;
        this.voteCount = voteCount;
    }
    @Generated(1094291921)
    public Comments() {
    }
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getComment() {
        return comment;
    }
    public void setComment(String comment_text) {
        this.comment = comment_text;
    }
    public Date getCreated() {
        return created;
    }
    public void setCreated(Date created) {
        this.created = created;
    }
    public int getVoteCount() {
        return voteCount;
    }
    public void setVoteCount(int voteCount) {
        this.voteCount = voteCount;
    }
}
