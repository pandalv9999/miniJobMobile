package com.example.minijobmobile.main.recommendation;

import androidx.lifecycle.MutableLiveData;

import com.example.minijobmobile.base.BaseModel;
import com.example.minijobmobile.main.Item;
import com.example.minijobmobile.remote.response.FavoriteItemResponse;
import com.example.minijobmobile.util.Config;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecommendationModel extends BaseModel {
    public MutableLiveData<List<Item>> GetRecommendation() {
        final MutableLiveData<List<Item>> result = new MutableLiveData<>();
        // retrieve user-related data
        Call<List<Item>> call = apiService.recommend(Config.latitude, Config.longitude,
                Config.getInstance().getUserId());
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

    public MutableLiveData<FavoriteItemResponse> call(Item item, boolean favorite) {
        return favorite ? addFavorite(item) : deleteFavorite(item);
    }

    public MutableLiveData<FavoriteItemResponse> addFavorite(Item item) {
        MutableLiveData<FavoriteItemResponse>
                favoriteItemResponseMutableLiveData = new MutableLiveData<>();
        FavoriteItemResponse body = new FavoriteItemResponse();
        body.setItem(item);
        body.setUserId(Config.getInstance().getUserId());
        Call<FavoriteItemResponse> call = apiService.addFavorite(body);
        call.enqueue(new Callback<FavoriteItemResponse>() {
            @Override
            public void onResponse(Call<FavoriteItemResponse> call, Response<FavoriteItemResponse> response) {
                favoriteItemResponseMutableLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<FavoriteItemResponse> call, Throwable t) {
                favoriteItemResponseMutableLiveData.setValue(null);
            }
        });
        return favoriteItemResponseMutableLiveData;
    }

    public MutableLiveData<FavoriteItemResponse> deleteFavorite(Item item) {
        MutableLiveData<FavoriteItemResponse>
                favoriteItemResponseMutableLiveData = new MutableLiveData<>();
        FavoriteItemResponse body = new FavoriteItemResponse();
        body.setItem(item);
        body.setUserId(Config.getInstance().getUserId());
        Call<FavoriteItemResponse> call = apiService.deleteFavorite(body);
        call.enqueue(new Callback<FavoriteItemResponse>() {
            @Override
            public void onResponse(Call<FavoriteItemResponse> call, Response<FavoriteItemResponse> response) {
                favoriteItemResponseMutableLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<FavoriteItemResponse> call, Throwable t) {
                favoriteItemResponseMutableLiveData.setValue(null);
            }
        });
        return favoriteItemResponseMutableLiveData;
    }
}
