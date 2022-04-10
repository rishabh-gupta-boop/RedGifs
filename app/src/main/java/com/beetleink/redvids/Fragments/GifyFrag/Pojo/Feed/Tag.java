
package com.beetleink.redvids.Fragments.GifyFrag.Pojo.Feed;

import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class Tag {

    @SerializedName("gifs")
    @Expose
    private List<Gif> gifs = null;
    @SerializedName("tag")
    @Expose
    private String tag;
    @SerializedName("tagText")
    @Expose
    private String tagText;
    @SerializedName("count")
    @Expose
    private Integer count;

    public List<Gif> getGifs() {
        return gifs;
    }

    public void setGifs(List<Gif> gifs) {
        this.gifs = gifs;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getTagText() {
        return tagText;
    }

    public void setTagText(String tagText) {
        this.tagText = tagText;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

}
