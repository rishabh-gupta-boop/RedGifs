package com.beetleink.redvids.Fragments.GifyFrag;

import android.icu.text.MessagePattern;

import com.beetleink.redvids.Fragments.GifyFrag.Pojo.Feed.Feed;



import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface HomePageTrendingApi {


    @GET("/v1/featured/categories/populated")
    Call<Feed> getGifs();


}
