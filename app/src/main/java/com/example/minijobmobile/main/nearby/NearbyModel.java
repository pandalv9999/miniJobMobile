package com.example.minijobmobile.main.nearby;

import androidx.lifecycle.MutableLiveData;

import com.example.minijobmobile.base.BaseModel;
import com.example.minijobmobile.main.Item;
import com.example.minijobmobile.util.Config;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NearbyModel extends BaseModel {
    public MutableLiveData<List<Item>> searchNearby() {
        final MutableLiveData<List<Item>> result = new MutableLiveData<>();
        // retrieve user-related data
        Config config = Config.getInstance();
        Call<List<Item>> call = apiService.search(37.38, -122.08, config.getUserId());
        call.enqueue(new Callback<List<Item>>() {
            @Override
            public void onResponse(Call<List<Item>> call, Response<List<Item>> response) {
                result.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<Item>> call, Throwable t) {
                result.setValue(null);
            }
        });
        return result;
    }
}
