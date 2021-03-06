package com.example.minijobmobile.main.nearby;

import androidx.lifecycle.MutableLiveData;

import com.example.minijobmobile.base.BaseViewModel;
import com.example.minijobmobile.main.Item;
import com.example.minijobmobile.remote.RemoteRequestListener;

import java.util.List;

public class NearbyViewModel extends BaseViewModel<NearbyModel> {

    private RemoteRequestListener remoteRequestListener = null;
    MutableLiveData<List<Item>> searchResult = model.searchNearby();

    protected NearbyViewModel(NearbyModel nearbyModel) {
        super(nearbyModel);
    }

    public void setRemoteRequestListener(RemoteRequestListener remoteRequestListener) {
        this.remoteRequestListener = remoteRequestListener;
    }

    public MutableLiveData<List<Item>> getSearchResult() {
        return searchResult;
    }

    public void processFavoriteItem(Item item, boolean favorite) {
        remoteRequestListener.onUpdate(model.call(item, favorite));
    }
}
