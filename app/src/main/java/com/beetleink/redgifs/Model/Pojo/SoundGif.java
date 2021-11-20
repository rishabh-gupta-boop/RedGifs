package com.beetleink.redgifs.Model.Pojo;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public HashMap<Urls,String> urls;
    public String userName;
    public int type;
    public String avgColor;
    public Object gallery;
    public HashMap<User,String> user;
}
