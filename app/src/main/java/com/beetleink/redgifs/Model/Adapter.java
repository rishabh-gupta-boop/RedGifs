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
import android.widget.VideoView;

import androidx.annotation.NonNull;

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

public class Adapter  extends RecyclerView.Adapter<Adapter.ViewHolder> {
    ArrayList<SoundGif> soundGifs = new ArrayList<>();
    Context context;
    boolean isActivityRunning = false;

    ///Calling soundgifs variables in arraylist//


    public Adapter(Context context,ArrayList<SoundGif> soundGifs) {
        this.soundGifs= soundGifs;
        this.context = context;
    }
    public Adapter(){}





    @NonNull
    @Override
    public Adapter.ViewHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_layout_main,parent,false);
        return new ViewHolder(view);
    }




    @Override
    public void onBindViewHolder(@NonNull  Adapter.ViewHolder holder, int position) {


    }



    @Override
    public void onViewAttachedToWindow(@NonNull  Adapter.ViewHolder holder) {
        super.onViewAttachedToWindow(holder);
        Log.i("onViewAttachedToWindow", "yes"+String.valueOf(holder.getPosition()));
        holder.onPrepare(holder.getPosition());
    }

    @Override
    public void onViewDetachedFromWindow(@NonNull  Adapter.ViewHolder holder) {
        super.onViewDetachedFromWindow(holder);
        Log.i("onViewDchedFromWindow", "yes"+String.valueOf(holder.getPosition()));
        holder.onDetach();
    }



    @Override
    public int getItemCount() {
        return soundGifs.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView disc;
        PlayerView viewPager2View;
        ProgressBar progressBar;
        ImageView thumbnail;
        ExoPlayer player;



        public ViewHolder(@NonNull  View itemView) {
            super(itemView);
            disc = itemView.findViewById(R.id.disk);
            Glide.with(context).load(R.drawable.disk).into(disc);
            progressBar = itemView.findViewById(R.id.progressBar);

            //imageView
            thumbnail = itemView.findViewById(R.id.thumbnail);
            thumbnail.setVisibility(View.GONE);
            //videoview and player
            viewPager2View = itemView.findViewById(R.id.viewPager2View);

        }

        public void onPrepare(int position) {
            player = new ExoPlayer.Builder(context).build();
            player.setRepeatMode(Player.REPEAT_MODE_ONE);
            MediaItem mediaItem = MediaItem.fromUri(soundGifs.get(position).urls.get("sd"));
            player.setMediaItem(mediaItem);
            Glide.with(context).load(soundGifs.get(position).urls.get("thumbnail"));
            player.prepare();
            viewPager2View.setPlayer(player);
            player.addListener(new Player.Listener() {
                @Override
                public void onPlaybackStateChanged(int playbackState) {

                    if(playbackState==2){
                        thumbnail.setVisibility(View.VISIBLE);
                    }
                    if(playbackState==3){
                        thumbnail.setVisibility(View.GONE);
                        player.play();
                    }
                }
            });
        }

        public void onDetach() {
            player.pause();
            player.release();

        }
        public void releasePlayer() {
            if (player != null) {
                player.release();
                player.clearVideoSurface();
                viewPager2View.getPlayer().release();
                player = null;
                viewPager2View = null;
            }
        }


    }

}
