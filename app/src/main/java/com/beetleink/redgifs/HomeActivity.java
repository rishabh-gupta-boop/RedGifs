package com.beetleink.redgifs;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import com.beetleink.redgifs.Model.Adapter;

import com.beetleink.redgifs.Model.Pojo.SoundGif;
import com.beetleink.redgifs.Model.Pojo.Urls;
import com.beetleink.redgifs.Model.Pojo.User;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.sql.SQLOutput;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;


public class HomeActivity extends AppCompatActivity {
    private ImageView disc;
    private ViewPager2 viewPager2;
    Adapter arrayAdapter;
    boolean isActivityRunning = false;

    ArrayList<SoundGif> soundGifs = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        isActivityRunning = true;

        init();

    }

    @Override
    protected void onResume() {
        super.onResume();
        isActivityRunning = true;
    }

    @Override
    public void onPause() {
        super.onPause();
        isActivityRunning = false;
        Adapter.ViewHolder viewHolder = null;
        viewHolder.releasePlayer();
    }

    @Override
    public void onStop() {
        super.onStop();
        isActivityRunning = false;
        Adapter.ViewHolder viewHolder = null;
        viewHolder.releasePlayer();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Adapter.ViewHolder viewHolder = null;
        viewHolder.releasePlayer();
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        Adapter.ViewHolder viewHolder = null;
        viewHolder.releasePlayer();
    }

    private void init() {

        viewPager2 = findViewById(R.id.viewPager2);

        FirebaseDatabase.getInstance("https://redgifs-6739a-default-rtdb.asia-southeast1.firebasedatabase.app/")
                .getReference().child("soundGifs")
                .addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(@NonNull  DataSnapshot snapshot, @Nullable  String previousChildName) {
                        SoundGif soundGif = new SoundGif();
                        soundGif.avgColor = snapshot.child("avgColor").getValue().toString();
                        soundGif.createDate = (int) snapshot.child("createDate").getValue(Integer.class);
                        soundGif.duration = (int) snapshot.child("duration").getValue(Integer.class);
                        soundGif.hasAudio = (boolean) snapshot.child("hasAudio").getValue(boolean.class);
                        soundGif.height = (int) snapshot.child("height").getValue(Integer.class);
                        soundGif.id = snapshot.child("id").getValue().toString();
                        soundGif.likes = (int) snapshot.child("likes").getValue(Integer.class);
                        soundGif.published = (boolean) snapshot.child("published").getValue(boolean.class);
                        soundGif.tags = (List<String>) snapshot.child("tags").getValue();
                        soundGif.type = (int) snapshot.child("type").getValue(Integer.class);
                        soundGif.urls = (HashMap<Urls, String>) snapshot.child("urls").getValue();
                        soundGif.user = (HashMap<User, String>) snapshot.child("user").getValue();
                        soundGif.userName =  snapshot.child("userName").getValue().toString();
                        soundGif.verified =  (boolean) snapshot.child("verified").getValue(boolean.class);
                        soundGif.views =  (int) snapshot.child("views").getValue(Integer.class);
                        soundGif.width =  (int) snapshot.child("width").getValue(Integer.class);
                        soundGifs.add(soundGif);
                        arrayAdapter = new Adapter(getApplicationContext(),soundGifs);
                        viewPager2.setAdapter(arrayAdapter);



                    }

                    @Override
                    public void onChildChanged(@NonNull  DataSnapshot snapshot, @Nullable  String previousChildName) {

                    }

                    @Override
                    public void onChildRemoved(@NonNull  DataSnapshot snapshot) {}

                    @Override
                    public void onChildMoved(@NonNull  DataSnapshot snapshot, @Nullable  String previousChildName) {}

                    @Override
                    public void onCancelled(@NonNull  DatabaseError error) {}
                });




    }





}