package com.beetleink.redvids.Fragments.PersonFrag;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.beetleink.redvids.Fragments.GifyFrag.Adapter;
import com.beetleink.redvids.Fragments.GifyFrag.HomePageTrendingApi;
import com.beetleink.redvids.Fragments.GifyFrag.Pojo.Feed.TrendingAccountGifsAndDetails.Gif;
import com.beetleink.redvids.Fragments.GifyFrag.Pojo.Feed.TrendingAccountGifsAndDetails.TopAccountBestGifs;
import com.beetleink.redvids.Fragments.GifyFrag.Pojo.UserProfile.UserProfile;
import com.beetleink.redvids.Fragments.PersonFrag.Authentication.LoginActivity;
import com.beetleink.redvids.Fragments.PersonFrag.Creator.CreatorAdapter;
import com.beetleink.redvids.Fragments.PersonFrag.Viewer.ViewerAdapter;
import com.beetleink.redvids.R;
import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class PersonFragment extends Fragment {
    ImageView profilePic;
    TextView userNameTextView, descriptionTextView,totalViewTextView,totalFollowerTextView;
    AppCompatButton followOrEditButtonView;
    RecyclerView usersPostsList;
    ArrayList<Integer> arrayListImages;
    ListView listView;
    Map<String, String> userDetails;
    ArrayList<String> userGifsList;
    ArrayList<String> likes;
    ArrayList<String> username;
    ArrayList<List<String>> tags;
    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance("https://redgifs-6739a-default-rtdb.asia-southeast1.firebasedatabase.app/");
    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    HomePageTrendingApi homePageFeedApi;
    Retrofit retrofit;
    ArrayList<String> userGifsThumbnailList;







    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.creator_fragment, container, false);
        userGifsList = new ArrayList<>();
        retrofit  = new Retrofit.Builder()
                .baseUrl("https://api.redgifs.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        homePageFeedApi = retrofit.create(HomePageTrendingApi.class);


        creatorFragment(view);
//        viewerFragment(view);



      return view;
    }


    void creatorFragment(View view){

        //initialisation
        profilePic = view.findViewById(R.id.profile_image);
        userNameTextView = view.findViewById(R.id.userNameTextView);
        descriptionTextView = view.findViewById(R.id.descriptionTextView);
        totalFollowerTextView = view.findViewById(R.id.totalFollowerTextView);
        totalViewTextView = view.findViewById(R.id.totalViewTextView);
        usersPostsList = view.findViewById(R.id.usersPostsList);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),3);
        usersPostsList.setLayoutManager(gridLayoutManager);


        userGifsList= new ArrayList<>();
        userGifsThumbnailList = new ArrayList<>();
        likes= new ArrayList<>();
        username= new ArrayList<>();
        tags= new ArrayList<>();



//        getUserProfile();


        FirebaseDatabase.getInstance().getReference().child("users").child(FirebaseAuth.getInstance().getUid())
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull  DataSnapshot snapshot) {
                        getUserProfile(snapshot.child("token").getValue().toString(),snapshot.child("tokenType").getValue().toString());

                    }

                    @Override
                    public void onCancelled(@NonNull  DatabaseError error) {

                    }
                });

    }



    //get own login profile after authentication
    public void getUserProfile(String token,String tokenType){
        userDetails = new HashMap<>();
        Log.i("userProfilet", tokenType);
        Call<UserProfile> userProfile = homePageFeedApi.getUserProfile(tokenType+" "+token);
        userProfile.enqueue(new Callback<UserProfile>() {
            @Override
            public void onResponse(Call<com.beetleink.redvids.Fragments.GifyFrag.Pojo.UserProfile.UserProfile> call, Response<UserProfile> response) {
                if(response.isSuccessful()){
//                    Log.i("userProfile", response.body().getProfileImageUrl());
                    userDetails.put("username", response.body().getUsername());
                    userDetails.put("profileImage", response.body().getProfileImageUrl());
                    userDetails.put("views", String.valueOf(response.body().getViews()));
                    userDetails.put("followers", String.valueOf(response.body().getFollowers()));
                    userNameTextView.setText(response.body().getUsername());
                    totalViewTextView.setText(String.valueOf(response.body().getViews()));
                    totalFollowerTextView.setText(String.valueOf(response.body().getFollowers()));
                    if(response.body().getProfileImageUrl()!=null){
                        Glide.with(PersonFragment.this).load(response.body().getProfileImageUrl()).into(profilePic);

                    }
                    getGifsListOfUserName();

                }else{
                    Log.i("userProfile", String.valueOf(response.code()));
                }
            }

            @Override
            public void onFailure(Call<UserProfile> call, Throwable t) {
                Log.i("userProfile", t.getMessage());
            }
        });

    }

    public void getGifsListOfUserName(){
        Call<TopAccountBestGifs> topAccountBestGifsCall = homePageFeedApi.getAccountGifsAndDetails(userDetails.get("username"),"recent","g",1);
        topAccountBestGifsCall.enqueue(new Callback<TopAccountBestGifs>() {
            @Override
            public void onResponse(Call<TopAccountBestGifs> call, Response<TopAccountBestGifs> response) {
                if(response.isSuccessful()){
                    List<Gif> gifs = response.body().getGifs();
                    for(Gif sdGifs: gifs){
                        Log.i("sdGifs", sdGifs.getUrls().getSd());
                        userGifsList.add(sdGifs.getUrls().getSd());
                        userGifsThumbnailList.add(sdGifs.getUrls().getThumbnail());
                        likes.add(sdGifs.getLikes().toString());
                        username.add(sdGifs.getUserName());
                        tags.add(sdGifs.getTags());
                        Log.i("asdfasdf", userGifsThumbnailList.get(0));
                        CreatorAdapter adapter = new CreatorAdapter(getContext(), userGifsThumbnailList);
                        usersPostsList.setAdapter(adapter);

                    }
                }else{
                    Log.i("asdfasdfHereBitch", String.valueOf(response.code()));
                }



            }

            @Override
            public void onFailure(Call<TopAccountBestGifs> call, Throwable t) {

            }

        });

    }

    void viewerFragment(View view){
        profilePic = view.findViewById(R.id.profile_image);
        followOrEditButtonView = view.findViewById(R.id.followOrEditTextView);
        userNameTextView = view.findViewById(R.id.userNameTextView);
        EditButton(view);


        listView = view.findViewById(R.id.notificationListView);
        String countryNames[] = {
                "India",
                "China",
                "Nepal",
                "Bhutan"
        };

        String capitalNames[] = {
                "Delhi",
                "Beijing",
                "Kathmandu",
                "Thimphu"
        };

        Integer imageid[] = {
                R.drawable.a,
                R.drawable.b,
                R.drawable.c,
                R.drawable.d

        };

        ////
        // Setting header
        TextView textView = new TextView(getContext());
        textView.setTypeface(Typeface.DEFAULT_BOLD);
        textView.setText("List of Countries");


        listView.addHeaderView(textView);

        // For populating list data
        ViewerAdapter viewerAdapter = new ViewerAdapter(getActivity(), countryNames, capitalNames, imageid);
        listView.setAdapter(viewerAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Toast.makeText(getContext(),"You Selected "+countryNames[position-1]+ " as Country",Toast.LENGTH_SHORT).show();        }
        });

        //firebase username change according to the firebase Database
        if(firebaseAuth.getCurrentUser()!=null){
            firebaseDatabase.getReference().child("users").child(firebaseAuth.getUid()).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot snapshot) {
                    userNameTextView.setText(snapshot.child("username").getValue().toString());
                }
                @Override
                public void onCancelled(DatabaseError error){
                    Log.i("Errorrr", error.getMessage());
                }
            });
        }

    }

    void EditButton(View view){
        followOrEditButtonView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(),EditAccount.class);
                startActivity(intent);
            }
        });
    }







    }





