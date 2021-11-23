package com.beetleink.redgifs.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.beetleink.redgifs.Model.Adapter;
import com.beetleink.redgifs.Model.Pojo.SoundGif;
import com.beetleink.redgifs.Model.Pojo.Urls;
import com.beetleink.redgifs.Model.Pojo.User;
import com.beetleink.redgifs.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GifyView extends Fragment {
    ViewPager2 viewPager2;
//    ArrayList<SoundGif> soundGifs = new ArrayList<>();
    ArrayList<String> locationArrayList = new ArrayList<>();
    Adapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_gify_view, container, false);
        viewPager2 = view.findViewById(R.id.viewPager2);

        init(view);
        return view;
    }




    void init(View view){
        //fake video define
        locationArrayList.add("android.resource://" + getContext().getPackageName()+"/"+R.raw.a);
        locationArrayList.add("android.resource://" + getContext().getPackageName()+"/"+R.raw.b);
        locationArrayList.add("android.resource://" + getContext().getPackageName()+"/"+R.raw.c);
        locationArrayList.add("android.resource://" + getContext().getPackageName()+"/"+R.raw.a);
        locationArrayList.add("android.resource://" + getContext().getPackageName()+"/"+R.raw.b);
        locationArrayList.add("android.resource://" + getContext().getPackageName()+"/"+R.raw.c);
        locationArrayList.add("android.resource://" + getContext().getPackageName()+"/"+R.raw.a);
        locationArrayList.add("android.resource://" + getContext().getPackageName()+"/"+R.raw.b);
        locationArrayList.add("android.resource://" + getContext().getPackageName()+"/"+R.raw.c);
        adapter = new Adapter(view.getContext(),locationArrayList);
        viewPager2.setAdapter(adapter);




        //gettting data from firebase database and put in soundgifs arraylist
        FirebaseDatabase.getInstance("https://redgifs-6739a-default-rtdb.asia-southeast1.firebasedatabase.app/")
                .getReference().child("soundGifs")
                .addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
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
//                        soundGifs.add(soundGif);
//                        adapter = new Adapter(view.getContext(),soundGifs);
//                        viewPager2.setAdapter(adapter);
                    }

                    @Override
                    public void onChildChanged(@NonNull  DataSnapshot snapshot, @Nullable  String previousChildName) {

                    }

                    @Override
                    public void onChildRemoved(@NonNull  DataSnapshot snapshot) {}

                    @Override
                    public void onChildMoved(@NonNull  DataSnapshot snapshot, @Nullable  String previousChildName) {}

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {}
                });

    }



}