package com.example.minijobmobile.onboarding.login;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.minijobmobile.base.BaseModel;
import com.example.minijobmobile.remote.response.OnBoardingResponse;
import com.example.minijobmobile.util.Utils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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

    public MutableLiveData<OnBoardingResponse> userLogin() {
        MutableLiveData<OnBoardingResponse> loginResponse = new MutableLiveData<>();
        String encryptedPassword = Utils.md5Encryption(password);
        OnBoardingResponse onBoardingResponse = new OnBoardingResponse();
        onBoardingResponse.setUser_id(userId);
        onBoardingResponse.setPassword(encryptedPassword);

        Call<OnBoardingResponse> call = apiService.login(onBoardingResponse);
        call.enqueue(new Callback<OnBoardingResponse>() {
            @Override
            public void onResponse(Call<OnBoardingResponse> call, Response<OnBoardingResponse> response) {
                if (response.code() == 200) {
                    loginResponse.setValue(response.body());
                } else {
                    onBoardingResponse.setStatus("Invalid username or password");
                    loginResponse.setValue(onBoardingResponse);
                }

            }

            @Override
            public void onFailure(Call<OnBoardingResponse> call, Throwable t) {
                onBoardingResponse.setStatus(t.getMessage());
                loginResponse.setValue(onBoardingResponse);
            }
        });
        return loginResponse;
    }
}
