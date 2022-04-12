package com.beetleink.redvids.Fragments.GifyFrag;

import com.beetleink.redvids.Fragments.GifyFrag.Pojo.Feed.TopTrendingAccount.Feed;
import com.beetleink.redvids.Fragments.GifyFrag.Pojo.Feed.TrendingAccountGifsAndDetails.TopAccountBestGifs;
import com.beetleink.redvids.Fragments.GifyFrag.Pojo.Login.ReceiverLoginCred;
import com.beetleink.redvids.Fragments.GifyFrag.Pojo.UserProfile.UserProfile;


import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface HomePageTrendingApi {


    @GET("v1/creators/search?page=1&order=top28")
    Call<Feed> getTrendingUserName(
            @Query("page") Integer pageNumber,
            @Query("order") String top28);

    @FormUrlEncoded
    @POST("v2/oauth/login")
    Call<ReceiverLoginCred> sendLogin(
            @Field("grant_type") String grant_type,
            @Field("username") String username,
            @Field("password") String password);

    @GET("v1/me")
    Call<UserProfile> getUserProfile(
            @Header("Authorization") String authToken
    );

    @GET("v2/users/{userId}/search")
    Call<TopAccountBestGifs> getAccountGifsAndDetails(
            @Path("userId") String userId,
            @Query("order") String OrderType,
            @Query("type") String typeType,
            @Query("page") Integer PageNumber
    );


}
