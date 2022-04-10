package com.beetleink.redvids.Fragments.GifyFrag;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.beetleink.redvids.Fragments.GifyFrag.Pojo.Feed.Gif;
import com.beetleink.redvids.Fragments.GifyFrag.Pojo.Feed.Tag;
import com.beetleink.redvids.Fragments.GifyFrag.Pojo.Feed.Urls;

import com.beetleink.redvids.Fragments.GifyFrag.Pojo.Feed.Feed;

import com.beetleink.redvids.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Url;

public class GifyView extends Fragment {
    ViewPager2 viewPager2;
//    ArrayList<SoundGif> soundGifs = new ArrayList<>();
    ArrayList<String> locationArrayList = new ArrayList<>();
    ArrayList<String> likes,description,username, comments,profileImage;
    ArrayList<List<String>> tags;

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
        List<String> sdvideoList = new ArrayList<>();
        likes = new ArrayList<>();
        username = new ArrayList<>();
        tags = new ArrayList<>();
        Retrofit retrofit  = new Retrofit.Builder()
                .baseUrl("https://api.redgifs.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        HomePageTrendingApi homePageFeedApi = retrofit.create(HomePageTrendingApi.class);
        Call<Feed> feed = homePageFeedApi.getGifs();
        feed.enqueue(new Callback<Feed>() {
            @Override
            public void onResponse(Call<Feed> call, Response<Feed> response) {
                List<Tag> tagList = response.body().getTags();
                for(Tag gifs: tagList){
                    for( Gif gif: gifs.getGifs()){
                        likes.add(gif.getLikes().toString());
                        username.add(gif.getUserName());
                        tags.add( gif.getTags());



                        sdvideoList.add(gif.getUrls().getSd());
                        if(gif.getUrls().getSd()!=null){
                            //fake video define
                            locationArrayList.add(gif.getUrls().getSd());


                            adapter = new Adapter(view.getContext(),locationArrayList,likes,username,tags);
                            viewPager2.setAdapter(adapter);
                        }

                    }
                }
            }

            @Override
            public void onFailure(Call<Feed> call, Throwable t) {

            }
        });








        //gettting data from firebase database and put in soundgifs arraylist
////        FirebaseDatabase.getInstance("https://redgifs-6739a-default-rtdb.asia-southeast1.firebasedatabase.app/")
////                .getReference().child("soundGifs")
////                .addChildEventListener(new ChildEventListener() {
////                    @Override
////                    public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
////                        SoundGif soundGif = new SoundGif();
////                        soundGif.avgColor = snapshot.child("avgColor").getValue().toString();
////                        soundGif.createDate = (int) snapshot.child("createDate").getValue(Integer.class);
////                        soundGif.duration = (int) snapshot.child("duration").getValue(Integer.class);
////                        soundGif.hasAudio = (boolean) snapshot.child("hasAudio").getValue(boolean.class);
////                        soundGif.height = (int) snapshot.child("height").getValue(Integer.class);
////                        soundGif.id = snapshot.child("id").getValue().toString();
////                        soundGif.likes = (int) snapshot.child("likes").getValue(Integer.class);
////                        soundGif.published = (boolean) snapshot.child("published").getValue(boolean.class);
////                        soundGif.tags = (List<String>) snapshot.child("tags").getValue();
////                        soundGif.type = (int) snapshot.child("type").getValue(Integer.class);
////                        soundGif.urls = (HashMap<Urls, String>) snapshot.child("urls").getValue();
////                        soundGif.user = (HashMap<User, String>) snapshot.child("user").getValue();
////                        soundGif.userName =  snapshot.child("userName").getValue().toString();
////                        soundGif.verified =  (boolean) snapshot.child("verified").getValue(boolean.class);
////                        soundGif.views =  (int) snapshot.child("views").getValue(Integer.class);
////                        soundGif.width =  (int) snapshot.child("width").getValue(Integer.class);
//////                        soundGifs.add(soundGif);
//////                        adapter = new Adapter(view.getContext(),soundGifs);
//////                        viewPager2.setAdapter(adapter);
////                    }
//
//                    @Override
//                    public void onChildChanged(@NonNull  DataSnapshot snapshot, @Nullable  String previousChildName) {
//
//                    }
//
//                    @Override
//                    public void onChildRemoved(@NonNull  DataSnapshot snapshot) {}
//
//                    @Override
//                    public void onChildMoved(@NonNull  DataSnapshot snapshot, @Nullable  String previousChildName) {}
//
//                    @Override
//                    public void onCancelled(@NonNull DatabaseError error) {}
//                });

    }



}