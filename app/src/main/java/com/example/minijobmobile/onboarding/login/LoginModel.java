package com.example.minijobmobile.onboarding.login;

import com.example.minijobmobile.base.BaseModel;

public class LoginModel extends BaseModel {
    private String userId;
    private String password;

    public LoginModel(){}

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
