package com.beetleink.redgifs.Model;


import android.content.Context;
import android.media.MediaPlayer;
import android.media.ThumbnailUtils;
import android.util.Log;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.annotation.NonNull;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.beetleink.redgifs.Model.Pojo.SoundGif;
import com.beetleink.redgifs.Model.Pojo.Urls;
import com.beetleink.redgifs.Model.Pojo.User;
import com.beetleink.redgifs.R;
import com.bumptech.glide.Glide;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.ui.StyledPlayerView;
import com.google.firebase.database.DataSnapshot;

import org.xml.sax.helpers.AttributesImpl;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class Adapter  extends RecyclerView.Adapter<ViewHolder> {
//    ArrayList<SoundGif> soundGifs = new ArrayList<>();
    ArrayList<String> locationArrayList = new ArrayList<>();
    Context context;
    static public ViewHolder viewHolder;




        ///Calling soundgifs variables in arraylist//
//        ArrayList<SoundGif> soundGifs
    public Adapter(Context context,ArrayList<String> locationArrayList) {
        this.locationArrayList= locationArrayList;
        this.context = context;
        Log.i("pausework", "main yes");
    }

    public Adapter(){}


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_layout_main,parent,false);

        return new ViewHolder(view,context,locationArrayList);


    }


    @Override
    public void onBindViewHolder(@NonNull  ViewHolder holder, int position) {


    }


    @Override
    public void onViewAttachedToWindow(@NonNull  ViewHolder holder) {
        super.onViewAttachedToWindow(holder);
        Log.i("onViewAttachedToWindow", "yes"+String.valueOf(holder.getPosition()));
        viewHolder = holder;
//        holder.itemsFiled(holder.getPosition());
        holder.onPrepare(holder.getPosition());
        holder.resumePlayer();

    }

    @Override
    public void onViewDetachedFromWindow(@NonNull  ViewHolder holder) {
        super.onViewDetachedFromWindow(holder);
        Log.i("onViewDchedFromWindow", "yes"+String.valueOf(holder.getPosition()));
        holder.releasePlayer();


    }



    @Override
    public int getItemCount() {

//        return soundGifs.size();
        return locationArrayList.size();
    }



}
