package com.example.minijobmobile.remote;

import androidx.lifecycle.LiveData;

import com.example.minijobmobile.main.Item;
import com.example.minijobmobile.remote.response.OnBoardingResponse;

public interface RemoteRequestListener {

    public void onSuccess(LiveData<OnBoardingResponse> loginResponse);
    public void onUpdate(LiveData<Item> itemResponse);
    public void onFailure(String message);

}
