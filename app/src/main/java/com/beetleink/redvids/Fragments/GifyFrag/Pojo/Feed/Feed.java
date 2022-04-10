
package com.beetleink.redvids.Fragments.GifyFrag.Pojo.Feed;

import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class Feed {

    @SerializedName("tags")
    @Expose
    private List<Tag> tags = null;
    @SerializedName("cursor")
    @Expose
    private String cursor;

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public String getCursor() {
        return cursor;
    }

    public void setCursor(String cursor) {
        this.cursor = cursor;
    }

}
