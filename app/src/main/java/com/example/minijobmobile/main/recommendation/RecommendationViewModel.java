package com.example.minijobmobile.main.recommendation;

import androidx.lifecycle.MutableLiveData;

import com.example.minijobmobile.base.BaseViewModel;
import com.example.minijobmobile.main.Item;
import com.example.minijobmobile.main.favorite.FavoriteModel;
import com.example.minijobmobile.remote.RemoteRequestListener;

import java.util.List;

public class RecommendationViewModel extends BaseViewModel<RecommendationModel> {


    MutableLiveData<List<Item>> recommendList = model.GetRecommendation();
    private RemoteRequestListener remoteRequestListener = null;

    protected RecommendationViewModel(RecommendationModel baseModel) {
        super(baseModel);
    }

    public MutableLiveData<List<Item>> getFavoriteList() {
        return recommendList;
    }
    public void setRemoteRequestListener(RemoteRequestListener remoteRequestListener) {
        this.remoteRequestListener = remoteRequestListener;
    }

    public void processFavoriteItem(Item item, boolean favorite) {
        remoteRequestListener.onUpdate(model.call(item, favorite));
    }
}
