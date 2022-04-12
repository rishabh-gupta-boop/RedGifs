package com.beetleink.redvids.Fragments.GifyFrag;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.beetleink.redvids.Fragments.PersonFrag.Authentication.LoginActivity;
import com.beetleink.redvids.R;
import com.bumptech.glide.Glide;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.ui.PlayerView;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;



public  class ViewHolder extends  RecyclerView.ViewHolder{
    ImageView disc,imageLike,imageComment,share,thumbnail;
    PlayerView viewPager2View;

    ProgressBar progressBar;
    CircleImageView circular_dp;
    Player player;
    ArrayList<String> gifLikes,userName;
    ArrayList<List<String>> tags = new ArrayList<>();



    TextView account,description, musicName,like,comment;
    Context context;
//    ArrayList<SoundGif> soundGifs = new ArrayList<>();
    ArrayList<String> locationArrayList = new ArrayList<>();









    public ViewHolder(View itemView, Context context, ArrayList<String> locationArrayList,ArrayList<String> gifslikes, ArrayList<String> userName, ArrayList<List<String>> tags) {
        super(itemView);


        this.context = context;
//        this.soundGifs= soundGifs;
        this.locationArrayList = locationArrayList;
        this.gifLikes = gifslikes;
        this.userName = userName;
        this.tags = tags;




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

        imageLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(LoginActivity.token!=null){
                    ViewDialog alert = new ViewDialog();
                    alert.showDialog(context);
                }else{
                    ViewDialog alert = new ViewDialog();
                    alert.showDialog(context);
                }
            }
        });



    }




    public void onPrepare(int position) {
//        viewPager2View.setVideoPath(soundGifs.get(position).urls.get("sd"));
        like.setText(gifLikes.get(position));
        account.setText(userName.get(position));
        description.setText(tags.get(position).toString());
        player = new ExoPlayer.Builder(context).build();
        viewPager2View.setPlayer(player);
        MediaItem mediaItem = MediaItem.fromUri(locationArrayList.get(position));
        player.setMediaItem(mediaItem);
        player.prepare();
//        player.setVolume(0f);

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


class ViewDialog {

    public void showDialog(Context context){
        final Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.custom_dialog_to_alert_login_or_create_account);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.setCancelable(true);

        TextView loginNowButton = (TextView) dialog.findViewById(R.id.loginNowButton);
        TextView CreateNewAccount = (TextView) dialog.findViewById(R.id.CreateNewAccount);
        dialog.show();

    }
}
