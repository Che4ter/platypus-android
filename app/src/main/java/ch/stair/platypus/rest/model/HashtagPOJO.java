package ch.stair.platypus.rest.model;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class HashtagPOJO {
    @SerializedName("id")
    private Integer id;
    @SerializedName("hashtext")
    private String hashText;
    @SerializedName("hash_types_id")
    private Integer hashTypesId;
    @SerializedName("last_modified")
    private Date lastModified;

    public HashtagPOJO(Integer id, String hashText, Integer hashTypesId, Date lastModified) {
        this.id = id;
        this.hashText = hashText;
        this.hashTypesId = hashTypesId;
        this.lastModified = lastModified;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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
