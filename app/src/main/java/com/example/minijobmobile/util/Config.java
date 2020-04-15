package com.example.minijobmobile.util;

import android.content.Context;
import android.location.LocationManager;

public class Config {

    private String userId;
    private String firstName;

    public String getUserId() {
        return userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public static Config getInstance() {
        return instance;
    }

    private String lastName;
    private double latitude;

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    private double longitude;

    private static Config instance = null;

    private Config(String userId, String firstName, String lastName) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public static Config getInstance(String userId, String firstName, String lastName) {
        if (instance == null) {
            instance = new Config(userId, firstName, lastName);
        }

        return instance;
    }

}
