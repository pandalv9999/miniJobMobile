package com.example.minijobmobile.main.nearby;

import android.content.Intent;
import android.net.Uri;
import android.widget.Button;

import androidx.lifecycle.MutableLiveData;

import com.example.minijobmobile.R;
import com.example.minijobmobile.base.BaseViewModel;
import com.example.minijobmobile.main.Item;
import com.example.minijobmobile.main.ItemDataAdapter;

import java.util.List;

public class NearbyViewModel extends BaseViewModel<NearbyModel> {
    MutableLiveData<List<Item>> searchResult = model.searchNearby();
    protected NearbyViewModel(NearbyModel nearbyModel) {
        super(nearbyModel);
    }

    public MutableLiveData<List<Item>> getSearchResult() {
        return searchResult;
    }

    public void processFavoriteItem(Item item, boolean favorite) {

    }
}
