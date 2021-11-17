package com.beetleink.redgifs.Model;

import android.net.Uri;
import android.widget.ImageView;
import android.widget.VideoView;

public class Model {
    Uri videoView;
    Uri imageView;

    public Uri getVideoView() {
        return videoView;
    }

    public void setVideoView(Uri videoView) {
        this.videoView = videoView;
    }



    public Uri getImageView() {
        return imageView;
    }

    public void setImageView(Uri imageView) {
        this.imageView = imageView;
    }

}
