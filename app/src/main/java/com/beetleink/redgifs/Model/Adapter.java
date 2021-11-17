package com.beetleink.redgifs.Model;


import android.content.Context;
import android.media.MediaPlayer;
import android.util.Log;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.VideoView;

import androidx.annotation.NonNull;

import androidx.recyclerview.widget.RecyclerView;

import com.beetleink.redgifs.ApiCalling.SoundGif;
import com.beetleink.redgifs.R;
import com.bumptech.glide.Glide;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.ui.StyledPlayerView;

import org.xml.sax.helpers.AttributesImpl;

import java.util.ArrayList;
import java.util.List;

public class Adapter  extends RecyclerView.Adapter<Adapter.ViewHolder> {
    SoundGif  models=null;
    ArrayList<String> urls = new ArrayList<String>();
    Context context;
    Boolean playingState=true;
    int correntVideoPlayingPostion;
    public Adapter(SoundGif models, Context context) {
        this.models = models;
        this.context = context;
        Log.i("models", models.toString());
    }




    @NonNull
    @Override
    public Adapter.ViewHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_layout_main,parent,false);
        return new ViewHolder(view);
    }




    @Override
    public void onBindViewHolder(@NonNull  Adapter.ViewHolder holder, int position) {
        Log.i("this is shit",String.valueOf(position));

        holder.viewPager2View.setVideoPath(urls.get(position));
    }

    @Override
    public void onViewDetachedFromWindow(@NonNull  Adapter.ViewHolder holder) {
        super.onViewDetachedFromWindow(holder);
        //passed windows

    }


    @Override
    public void onViewAttachedToWindow(@NonNull  Adapter.ViewHolder holder) {
        super.onViewAttachedToWindow(holder);
    }



    @Override
    public int getItemCount() {
        return urls.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {


        ImageView disc;
        VideoView viewPager2View;
        ProgressBar progressBar;
        ImageView imageView;



        public ViewHolder(@NonNull  View itemView) {
            super(itemView);
            disc = itemView.findViewById(R.id.disk);
            Glide.with(context).load(R.drawable.disk).into(disc);

            //videoview and player
            viewPager2View = itemView.findViewById(R.id.viewPager2View);
            viewPager2View.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    Glide.with(context).load(R.drawable.a).into(imageView);
                    mp.start();
                }
            });

            

            viewPager2View.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    imageView.setVisibility(View.GONE);
                    mp.start();
                }
            });

            progressBar = itemView.findViewById(R.id.progressBar);




            //imageView
            imageView = itemView.findViewById(R.id.thumbnail);

        }






    }
}
