package com.example.minijobmobile.remote;


import com.example.minijobmobile.main.Item;
import com.example.minijobmobile.remote.response.FavoriteItemResponse;
import com.example.minijobmobile.remote.response.OnBoardingResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiService {
    @POST("jupiter/login")
    Call<OnBoardingResponse> login(@Body OnBoardingResponse credential);

    @POST("jupiter/register")
    Call<OnBoardingResponse> register(@Body OnBoardingResponse credential);

    @Headers({
            "Content-Type: application/json;charset=ISO-8859-1",
            "Connection: keep-alive"
    })
    @GET("jupiter/search")
    Call<List<Item>> search(@Query("lat") double latitude,
                            @Query("lon") double longitude,
                            @Query("user_id") String userId);

    @POST("jupiter/history")
    Call<FavoriteItemResponse> addFavorite(@Body FavoriteItemResponse item);

    @DELETE("jupiter/history")
    Call<FavoriteItemResponse> deleteFavorite(@Body FavoriteItemResponse item);
}
