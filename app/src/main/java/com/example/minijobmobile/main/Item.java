package com.example.minijobmobile.main;

import android.widget.ImageView;

import com.example.minijobmobile.R;
import com.google.gson.annotations.SerializedName;
import androidx.databinding.BindingAdapter;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

public class Item {

    @SerializedName("name")
    private String name;
    @SerializedName("keywords")
    private String[] keywords;
    @SerializedName("item_id")
    private String item_id;
    @SerializedName("address")
    private String address;
    @SerializedName("favorite")
    private boolean favorite;
    @SerializedName("image_url")
    private String image_url;
    @SerializedName("url")
    private String url;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String[] getKeywords() {
        return keywords;
    }

    public void setKeywords(String[] keywords) {
        this.keywords = keywords;
    }

    public String getItem_id() {
        return item_id;
    }

    public void setItem_id(String item_id) {
        this.item_id = item_id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean isFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @BindingAdapter({"image_url"})
    public static void loadImage(ImageView imageView, String image_url) {
        Glide.with(imageView.getContext())
                .setDefaultRequestOptions(new RequestOptions().circleCrop())
                .load(image_url)
                .placeholder(R.drawable.ic_recommendation)
                .into(imageView);
    }
}
