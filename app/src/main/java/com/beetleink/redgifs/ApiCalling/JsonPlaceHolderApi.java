package com.beetleink.redgifs.ApiCalling;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface JsonPlaceHolderApi {

    @GET("v2/home/feeds")
    Call<SoundGif> getSoundGifs();

    @GET("v2/home/feeds")
    Call<List<Urls>> getSDUrls();


}
