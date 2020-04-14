package com.example.minijobmobile.onboarding;

import androidx.lifecycle.LiveData;

import com.example.minijobmobile.remote.response.OnBoardingResponse;

public interface OnBoardingListener {

    public void onSuccess(LiveData<OnBoardingResponse> loginResponse);
    public void onFailure(String message);

}
