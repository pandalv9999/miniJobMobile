package com.example.minijobmobile.onboarding.register;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.minijobmobile.base.BaseModel;
import com.example.minijobmobile.remote.ApiUtils;
import com.example.minijobmobile.remote.response.OnBoardingResponse;
import com.example.minijobmobile.util.Utils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterModel extends BaseModel {
    private String userId;
    private String password;
    private String firstName;
    private String lastName;

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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public MutableLiveData<OnBoardingResponse> userRegister() {
        MutableLiveData<OnBoardingResponse> registerResponse = new MutableLiveData<>();
        String encryptedPassword = Utils.md5Encryption(password);
        OnBoardingResponse onBoardingResponse = new OnBoardingResponse();
        onBoardingResponse.setUser_id(userId);
        onBoardingResponse.setPassword(encryptedPassword);
        onBoardingResponse.setFirst_name(firstName);
        onBoardingResponse.setLast_name(lastName);

        Call<OnBoardingResponse> call = apiService.register(onBoardingResponse);
        call.enqueue(new Callback<OnBoardingResponse>() {
            @Override
            public void onResponse(Call<OnBoardingResponse> call, Response<OnBoardingResponse> response) {
                OnBoardingResponse body = response.body();
                if (response.code() == 200) {
                    registerResponse.setValue(response.body());
                } else {
                    onBoardingResponse.setStatus("User exist!");
                    registerResponse.setValue(onBoardingResponse);
                }
            }

            @Override
            public void onFailure(Call<OnBoardingResponse> call, Throwable t) {
                onBoardingResponse.setStatus(t.getMessage());
                registerResponse.setValue(onBoardingResponse);
            }
        });
        return registerResponse;
    }
}
