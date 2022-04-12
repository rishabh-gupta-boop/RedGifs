package com.beetleink.redvids.Fragments.GifyFrag;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.beetleink.redvids.Fragments.GifyFrag.Pojo.Feed.TopTrendingAccount.Feed;
import com.beetleink.redvids.Fragments.GifyFrag.Pojo.Feed.TopTrendingAccount.Item;
import com.beetleink.redvids.Fragments.GifyFrag.Pojo.Feed.TrendingAccountGifsAndDetails.Gif;
import com.beetleink.redvids.Fragments.GifyFrag.Pojo.Feed.TrendingAccountGifsAndDetails.TopAccountBestGifs;
import com.beetleink.redvids.Fragments.GifyFrag.Pojo.Login.ReceiverLoginCred;
import com.beetleink.redvids.Fragments.GifyFrag.Pojo.UserProfile.UserProfile;
import com.beetleink.redvids.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GifyView extends Fragment {
    ViewPager2 viewPager2;
//    ArrayList<SoundGif> soundGifs = new ArrayList<>();
//    ArrayList<String> locationArrayList = new ArrayList<>();
    ArrayList<String> likes,description,username, comments,profileImage;
    ArrayList<List<String>> tags;
    HomePageTrendingApi homePageFeedApi;
    Retrofit retrofit;
    Adapter adapter;
    List<String> trendinguserlist;
    ArrayList<String> sdUrlsList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_gify_view, container, false);

        viewPager2 = view.findViewById(R.id.viewPager2);
        //sending homefeed data
        retrofit  = new Retrofit.Builder()
                .baseUrl("https://api.redgifs.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        homePageFeedApi = retrofit.create(HomePageTrendingApi.class);
        init(view);


        return view;

    }




    void init(View view){
        //initialisation
        List<String> sdvideoList = new ArrayList<>();
        likes = new ArrayList<>();
        username = new ArrayList<>();
        tags = new ArrayList<>();
        trendinguserlist = new ArrayList<>();
        getTopUserList(1,view);
        sdUrlsList = new ArrayList<>();

    }


    //like and comment


    //------------------Feed-------------------------------------------//


    //getTrendingUserList
    public void getTopUserList(Integer pageNumber,View view) {
        Call<Feed> feed = homePageFeedApi.getTrendingUserName(pageNumber, "top28");
        feed.enqueue(new Callback<Feed>() {
            @Override
            public void onResponse(Call<Feed> call, Response<Feed> response) {
                if (response.isSuccessful()) {
                    List<Item> userList = response.body().getItems();
                    for (Item user : userList) {
                        trendinguserlist.add(user.getUsername());
                    }
                    top28GifsUrlsList(trendinguserlist,1,view);
                } else {
                    Log.i("successfull", String.valueOf(response.code()));
                }

            }

            @Override
            public void onFailure(Call<Feed> call, Throwable t) {

            }
        });
    }

    //getTrendingUserGifsAndDetails
    public void top28GifsUrlsList(List<String> trendinguserlist, Integer pageNumber,View view){
        Integer top25 = 0;

        for(String userName: trendinguserlist){
            if(top25<25){
                Call<TopAccountBestGifs> topAccountBestGifsCall = homePageFeedApi.getAccountGifsAndDetails(userName,"best","g",pageNumber);
                topAccountBestGifsCall.enqueue(new Callback<TopAccountBestGifs>() {
                    @Override
                    public void onResponse(Call<TopAccountBestGifs> call, Response<TopAccountBestGifs> response) {
                        if(response.isSuccessful()){
                            List<Gif> gifs = response.body().getGifs();
                            for(Gif sdGifs: gifs){
                                Log.i("sdGifs", sdGifs.getUrls().getSd());
                                sdUrlsList.add(sdGifs.getUrls().getSd());
                                likes.add(sdGifs.getLikes().toString());
                                username.add(sdGifs.getUserName());
                                tags.add(sdGifs.getTags());

                                break;
                            }
                        }else{
                            Log.i("asdfasdfHereBitch", String.valueOf(response.code()));
                        }

                        adapter = new Adapter(view.getContext(), sdUrlsList,likes,username,tags);
                        viewPager2.setAdapter(adapter);

                    }

                    @Override
                    public void onFailure(Call<TopAccountBestGifs> call, Throwable t) {

                    }

                });
            }

            top25++;
        }




    }










}

class HomeFeed{

}