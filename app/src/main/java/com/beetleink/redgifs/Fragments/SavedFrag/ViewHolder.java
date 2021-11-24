package com.beetleink.redgifs.Fragments.SavedFrag;

import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ViewHolder extends  RecyclerView.ViewHolder{
    ImageView imageView;
    public ViewHolder(@NonNull ImageView imageView) {
        super(imageView);
        this.imageView = imageView;

    }
}
