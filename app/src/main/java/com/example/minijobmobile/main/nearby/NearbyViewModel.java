package com.example.minijobmobile.main.nearby;

import androidx.lifecycle.MutableLiveData;

import com.example.minijobmobile.base.BaseModel;
import com.example.minijobmobile.base.BaseViewModel;
import com.example.minijobmobile.remote.response.ListItemQueryResponse;

import java.util.List;

public class NearbyViewModel extends BaseViewModel<NearbyModel> {
    MutableLiveData<List<ListItemQueryResponse>> searchResult = model.searchNearby();
    protected NearbyViewModel(NearbyModel nearbyModel) {
        super(nearbyModel);
    }

    public MutableLiveData<List<ListItemQueryResponse>> getSearchResult() {
        return searchResult;
    }
}
