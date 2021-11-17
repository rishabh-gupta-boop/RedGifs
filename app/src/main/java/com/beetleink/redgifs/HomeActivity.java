package com.beetleink.redgifs;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.text.PrecomputedTextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;
import androidx.viewpager2.widget.ViewPager2;

import android.app.Activity;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.widget.VideoView;

import com.beetleink.redgifs.ApiCalling.JsonPlaceHolderApi;
import com.beetleink.redgifs.ApiCalling.SoundGif;
import com.beetleink.redgifs.ApiCalling.Urls;
import com.beetleink.redgifs.Model.Adapter;
import com.beetleink.redgifs.Model.Model;
import com.bumptech.glide.Glide;

import org.json.JSONArray;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.graphics.PixelFormat.TRANSLUCENT;
import static android.provider.Telephony.TextBasedSmsColumns.STATUS;

public class HomeActivity extends AppCompatActivity {
    private ImageView disc;
    private ArrayList<Model> models;
    private ViewPager2 viewPager2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        init();

    }

    private void init(){
        models = new ArrayList<>();
        viewPager2 = findViewById(R.id.viewPager2);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.redgifs.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);

        Call<SoundGif> call = jsonPlaceHolderApi.getSoundGifs();
        call.enqueue(new Callback<SoundGif>() {
            @Override
            public void onResponse(Call<SoundGif> call, Response<SoundGif> response) {
                if(!response.isSuccessful()){
                    Log.i("errorrr", String.valueOf(response.code()));
                }
                
                Log.i("this is",response.body().toString());
//                ArrayList<Urls> urlsArray = new ArrayList<>();
//                urlsArray.addAll(response.body().getUrls()) ;
//                for(Urls urls:urlsArray){
//                    Log.i("ansesr", urls.sd);
//                }




//                Adapter adapter = new Adapter(response.body(),getApplicationContext());
//                viewPager2.setAdapter(adapter);


            }

            @Override
            public void onFailure(Call<SoundGif> call, Throwable t) {
                Log.i("errorrrr", t.getMessage());

            }
        });








    }



}