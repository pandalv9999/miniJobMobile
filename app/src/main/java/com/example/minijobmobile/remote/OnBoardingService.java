package com.example.minijobmobile.remote;


import com.example.minijobmobile.remote.response.OnBoardingResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface OnBoardingService {
    @POST("jupiter/login")
    Call<OnBoardingResponse> login(@Body OnBoardingResponse credential);

    @POST("jupiter/register")
    Call<OnBoardingResponse> register(@Body OnBoardingResponse credential);
}
