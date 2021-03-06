package ch.stair.platypus.repository.models;

import java.util.Date;
import java.util.List;

import io.objectbox.BoxStore;
import io.objectbox.annotation.Backlink;
import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Generated;
import io.objectbox.annotation.Id;
import io.objectbox.annotation.apihint.Internal;
import io.objectbox.relation.ToMany;
import io.objectbox.Box;

@Entity
public class Hashtag {
    @Id(assignable = true)
    private long id;
    private String hashText;
    private Integer hashTypesId;
    private Date lastModified;

    @Backlink
    public List<FeedbackHashtag> hashtagInFeedback = new ToMany<>(this, Hashtag_.hashtagInFeedback);
    /** Used to resolve relations */
    @Internal
    @Generated(1307364262)
    transient BoxStore __boxStore;

    @Generated(1706763340)
    @Internal
    /** This constructor was generated by ObjectBox and may change any time. */
    public Hashtag(long id, String hashText, Integer hashTypesId, Date lastModified) {
        this.id = id;
        this.hashText = hashText;
        this.hashTypesId = hashTypesId;
        this.lastModified = lastModified;
    }
    @Generated(21339749)
    public Hashtag() {
    }
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getHashText() {
        return hashText;
    }
    public void setHashText(String hashText) {
        this.hashText = hashText;
    }
    public Integer getHashTypesId() {
        return hashTypesId;
    }
    public void setHashTypesId(Integer hashTypesId) {
        this.hashTypesId = hashTypesId;
    }
    public Date getLastModified() {
        return lastModified;
    }
    public void setLastModified(Date lastModified) {
        this.lastModified = lastModified;
    }

}
