package com.example.minijobmobile.main.nearby;

import android.content.Intent;
import android.net.Uri;
import android.widget.Button;

import androidx.lifecycle.MutableLiveData;

import com.example.minijobmobile.R;
import com.example.minijobmobile.base.BaseViewModel;
import com.example.minijobmobile.main.Item;
import com.example.minijobmobile.main.ItemDataAdapter;
import com.example.minijobmobile.remote.RemoteRequestListener;
import com.example.minijobmobile.remote.response.FavoriteItemResponse;

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
        MutableLiveData<FavoriteItemResponse> responseMutableLiveData = model.call(item, favorite);
    }
}
