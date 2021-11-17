    package com.beetleink.redgifs.ApiCalling;
import com.beetleink.redgifs.ApiCalling.Urls;
import com.beetleink.redgifs.ApiCalling.User;

import java.util.List;
public class SoundGif{
    public String id;
    public int createDate;
    public boolean hasAudio;
    public int width;
    public int height;
    public int likes;
    public List<String> tags;
    public boolean verified;
    public int views;
    public int duration;
    public boolean published;



    public List<Urls> urls;
    public String userName;
    public int type;
    public String avgColor;
    public Object gallery;
    public User user;

    public List<Urls> getUrls() {
        return urls;
    }

    public String getId() {
        return id;
    }

    public int getCreateDate() {
        return createDate;
    }

    public boolean isHasAudio() {
        return hasAudio;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getLikes() {
        return likes;
    }

    public List<String> getTags() {
        return tags;
    }

    public boolean isVerified() {
        return verified;
    }

    public int getViews() {
        return views;
    }

    public int getDuration() {
        return duration;
    }

    public boolean isPublished() {
        return published;
    }



    public String getUserName() {
        return userName;
    }

    public int getType() {
        return type;
    }

    public String getAvgColor() {
        return avgColor;
    }

    public Object getGallery() {
        return gallery;
    }

    public User getUser() {
        return user;
    }
}
