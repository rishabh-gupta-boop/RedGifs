
package com.beetleink.redvids.Fragments.GifyFrag.Pojo.Feed;

import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class Gif {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("createDate")
    @Expose
    private Integer createDate;
    @SerializedName("hasAudio")
    @Expose
    private Boolean hasAudio;
    @SerializedName("width")
    @Expose
    private Integer width;
    @SerializedName("height")
    @Expose
    private Integer height;
    @SerializedName("likes")
    @Expose
    private Integer likes;
    @SerializedName("tags")
    @Expose
    private List<String> tags = null;
    @SerializedName("verified")
    @Expose
    private Boolean verified;
    @SerializedName("views")
    @Expose
    private Integer views;
    @SerializedName("duration")
    @Expose
    private Double duration;
    @SerializedName("published")
    @Expose
    private Boolean published;
    @SerializedName("urls")
    @Expose
    private Urls urls;
    @SerializedName("userName")
    @Expose
    private String userName;
    @SerializedName("type")
    @Expose
    private Integer type;
    @SerializedName("avgColor")
    @Expose
    private String avgColor;
    @SerializedName("gallery")
    @Expose
    private Object gallery;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Integer createDate) {
        this.createDate = createDate;
    }

    public Boolean getHasAudio() {
        return hasAudio;
    }

    public void setHasAudio(Boolean hasAudio) {
        this.hasAudio = hasAudio;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getLikes() {
        return likes;
    }

    public void setLikes(Integer likes) {
        this.likes = likes;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public Boolean getVerified() {
        return verified;
    }

    public void setVerified(Boolean verified) {
        this.verified = verified;
    }

    public Integer getViews() {
        return views;
    }

    public void setViews(Integer views) {
        this.views = views;
    }

    public Double getDuration() {
        return duration;
    }

    public void setDuration(Double duration) {
        this.duration = duration;
    }

    public Boolean getPublished() {
        return published;
    }

    public void setPublished(Boolean published) {
        this.published = published;
    }

    public Urls getUrls() {
        return urls;
    }

    public void setUrls(Urls urls) {
        this.urls = urls;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getAvgColor() {
        return avgColor;
    }

    public void setAvgColor(String avgColor) {
        this.avgColor = avgColor;
    }

    public Object getGallery() {
        return gallery;
    }

    public void setGallery(Object gallery) {
        this.gallery = gallery;
    }

}
