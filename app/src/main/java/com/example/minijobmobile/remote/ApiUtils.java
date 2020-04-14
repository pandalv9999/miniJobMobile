package com.example.minijobmobile.remote;

public class ApiUtils {

    public static final String BASE_URL = "http://18.216.222.29/"; //

    public static OnBoardingService getOnBoardingService() {
        return RetrofitClient.getClient(BASE_URL).create(OnBoardingService.class);
    }

}

