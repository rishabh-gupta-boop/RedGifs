package com.beetleink.redvids.Fragments.GifyFrag;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.beetleink.redvids.R;
import com.bumptech.glide.Glide;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.ui.PlayerView;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;



public  class ViewHolder extends  RecyclerView.ViewHolder{
    ImageView disc,imageLike,imageComment,share,thumbnail;
    PlayerView viewPager2View;

    ProgressBar progressBar;
    CircleImageView circular_dp;
    Player player;



    TextView account,description, musicName,like,comment;
    Context context;
//    ArrayList<SoundGif> soundGifs = new ArrayList<>();
    ArrayList<String> locationArrayList = new ArrayList<>();








    public ViewHolder(View itemView, Context context, ArrayList<String> locationArrayList) {
        super(itemView);
        this.context = context;
//        this.soundGifs= soundGifs;
        this.locationArrayList = locationArrayList;


        disc = itemView.findViewById(R.id.disk);
        like = itemView.findViewById(R.id.like);
        comment = itemView.findViewById(R.id.comment);
        share = itemView.findViewById(R.id.share);
        thumbnail = itemView.findViewById(R.id.thumbnail);
        circular_dp = itemView.findViewById(R.id.circular_dp);
        account = itemView.findViewById(R.id.account);
        description = itemView.findViewById(R.id.description);
        musicName = itemView.findViewById(R.id.musicName);
        imageComment=itemView.findViewById(R.id.imageComment);
        imageLike = itemView.findViewById(R.id.imageLike);


        Glide.with(context).load(R.drawable.disk).into(disc);
        progressBar = itemView.findViewById(R.id.progressBar);

        //imageView
        thumbnail.setVisibility(View.GONE);

        //videoview and player
        viewPager2View = itemView.findViewById(R.id.viewPager2View);




    }




    public void onPrepare(int position) {
//        viewPager2View.setVideoPath(soundGifs.get(position).urls.get("sd"));
        player = new ExoPlayer.Builder(context).build();
        viewPager2View.setPlayer(player);
        MediaItem mediaItem = MediaItem.fromUri(locationArrayList.get(position));
        player.setMediaItem(mediaItem);
        player.prepare();

    }

    public void pausePlayer() {
        player.pause();
        player.seekTo(0);


    }
    public  void releasePlayer() {
        player.release();
    }

    public void resumePlayer(){
        player.play();



    }





//    public void itemsFiled(int position){
//        like.setText(String.valueOf(soundGifs.get(position).likes));
//        account.setText(soundGifs.get(position).user.get("userName"));
//
//
//    }



}
