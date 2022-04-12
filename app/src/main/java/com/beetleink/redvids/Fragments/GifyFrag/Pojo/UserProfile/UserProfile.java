
package com.beetleink.redvids.Fragments.GifyFrag.Pojo.UserProfile;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class UserProfile {

    @SerializedName("creationtime")
    @Expose
    private Integer creationtime;
    @SerializedName("followers")
    @Expose
    private Integer followers;
    @SerializedName("following")
    @Expose
    private Integer following;
    @SerializedName("gifs")
    @Expose
    private Integer gifs;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("profileImageUrl")
    @Expose
    private String profileImageUrl;
    @SerializedName("profileUrl")
    @Expose
    private String profileUrl;
    @SerializedName("publishedGifs")
    @Expose
    private Integer publishedGifs;
    @SerializedName("subscription")
    @Expose
    private Integer subscription;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("verified")
    @Expose
    private Boolean verified;
    @SerializedName("views")
    @Expose
    private Integer views;

    public Integer getCreationtime() {
        return creationtime;
    }

    public void setCreationtime(Integer creationtime) {
        this.creationtime = creationtime;
    }

    public Integer getFollowers() {
        return followers;
    }

    public void setFollowers(Integer followers) {
        this.followers = followers;
    }

    public Integer getFollowing() {
        return following;
    }

    public void setFollowing(Integer following) {
        this.following = following;
    }

    public Integer getGifs() {
        return gifs;
    }

    public void setGifs(Integer gifs) {
        this.gifs = gifs;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfileImageUrl() {
        return profileImageUrl;
    }

    public void setProfileImageUrl(String profileImageUrl) {
        this.profileImageUrl = profileImageUrl;
    }

    public String getProfileUrl() {
        return profileUrl;
    }

    public void setProfileUrl(String profileUrl) {
        this.profileUrl = profileUrl;
    }

    public Integer getPublishedGifs() {
        return publishedGifs;
    }

    public void setPublishedGifs(Integer publishedGifs) {
        this.publishedGifs = publishedGifs;
    }

    public Integer getSubscription() {
        return subscription;
    }

    public void setSubscription(Integer subscription) {
        this.subscription = subscription;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

}
