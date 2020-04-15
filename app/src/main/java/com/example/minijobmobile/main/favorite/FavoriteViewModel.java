package com.example.minijobmobile.main.favorite;

import androidx.lifecycle.MutableLiveData;

import com.example.minijobmobile.base.BaseViewModel;
import com.example.minijobmobile.main.Item;
import com.example.minijobmobile.remote.RemoteRequestListener;

import java.util.List;

public class FavoriteViewModel extends BaseViewModel<FavoriteModel> {
    MutableLiveData<List<Item>> favoriteList = model.GetFavorite();
    private RemoteRequestListener remoteRequestListener = null;

    protected FavoriteViewModel(FavoriteModel baseModel) {
        super(baseModel);
    }

    public MutableLiveData<List<Item>> getFavoriteList() {
        return favoriteList;
    }
    public void setRemoteRequestListener(RemoteRequestListener remoteRequestListener) {
        this.remoteRequestListener = remoteRequestListener;
    }

    public void processFavoriteItem(Item item, boolean favorite) {
        remoteRequestListener.onUpdate(model.call(item, favorite));
    }
}
