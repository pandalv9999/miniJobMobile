package com.example.minijobmobile.main.nearby;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.minijobmobile.base.BaseModel;
import com.example.minijobmobile.remote.response.ListItemQueryResponse;
import com.example.minijobmobile.util.Config;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NearbyModel extends BaseModel {
    public MutableLiveData<List<ListItemQueryResponse>> searchNearby() {
        final MutableLiveData<List<ListItemQueryResponse>> result = new MutableLiveData<>();
        // retrieve user-related data
        Config config = Config.getInstance();
        Call<List<ListItemQueryResponse>> call = apiService.search(37.38, -122.08, config.getUserId());
        call.enqueue(new Callback<List<ListItemQueryResponse>>() {
            @Override
            public void onResponse(Call<List<ListItemQueryResponse>> call, Response<List<ListItemQueryResponse>> response) {
                result.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<ListItemQueryResponse>> call, Throwable t) {
                result.setValue(null);
            }
        });
        return result;
    }
}
