package com.example.minijobmobile.remote.response;

import com.example.minijobmobile.main.Item;
import com.google.gson.annotations.SerializedName;

public class FavoriteItemResponse {
    @SerializedName("user_id")
    private String userId;
    @SerializedName("favorite")
    private Item item;
    private String status;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
