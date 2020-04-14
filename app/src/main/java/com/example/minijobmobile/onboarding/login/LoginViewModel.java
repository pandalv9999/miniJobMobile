package com.example.minijobmobile.onboarding.login;

import android.content.Intent;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.minijobmobile.MainActivity;
import com.example.minijobmobile.base.BaseViewModel;

import com.example.minijobmobile.onboarding.OnBoardingListener;
import com.example.minijobmobile.remote.ApiUtils;
import com.example.minijobmobile.remote.response.OnBoardingResponse;
import com.example.minijobmobile.util.Utils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginViewModel extends BaseViewModel<LoginModel> {

    private OnBoardingListener onBoardingListener = null;

    public LoginViewModel(LoginModel loginModel) {
        super(loginModel);
    }

    public void setOnBoardingListener(OnBoardingListener onBoardingListener) {
        this.onBoardingListener = onBoardingListener;
    }

    public void setUserId(String userId) {
        model.setUserId(userId);
    }

    public void setPassword(String password) {
        model.setPassword(password);
    }

    public void Login() {
        final String userId = model.getUserId();
        if (userId == null) {
            onBoardingListener.onFailure("Please enter user ID");
            return;
        }
        final String password = model.getPassword();
        if (password == null) {
            onBoardingListener.onFailure("Please enter password.");
            return;
        }
        onBoardingListener.onSuccess(model.userLogin());
    }

}
