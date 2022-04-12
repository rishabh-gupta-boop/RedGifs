
package com.beetleink.redvids.Fragments.GifyFrag.Pojo.Feed.TrendingAccountGifsAndDetails;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class Urls {

    @SerializedName("sd")
    @Expose
    private String sd;
    @SerializedName("hd")
    @Expose
    private String hd;
    @SerializedName("poster")
    @Expose
    private String poster;
    @SerializedName("thumbnail")
    @Expose
    private String thumbnail;
    @SerializedName("vthumbnail")
    @Expose
    private String vthumbnail;

    public String getSd() {
        return sd;
    }

    public void setSd(String sd) {
        this.sd = sd;
    }

    public String getHd() {
        return hd;
    }

    public void setHd(String hd) {
        this.hd = hd;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getVthumbnail() {
        return vthumbnail;
    }

    public void setVthumbnail(String vthumbnail) {
        this.vthumbnail = vthumbnail;
    }

}
