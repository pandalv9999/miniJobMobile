package com.example.minijobmobile.onboarding.login;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.minijobmobile.base.BaseViewModel;
import com.example.minijobmobile.remote.ApiUtils;
import com.example.minijobmobile.remote.response.OnBoardingResponse;
import com.example.minijobmobile.util.Utils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginViewModel extends BaseViewModel<LoginModel> {

    private MutableLiveData<String> toastMsgObserver = new MutableLiveData<>();

    public LoginViewModel(LoginModel loginModel) {
        super(loginModel);
    }

    public void setUserId(String userId) {
        model.setUserId(userId);
    }

    public void setPassword(String password) {
        model.setPassword(password);
    }

    public LiveData<String> getToastObserver() {
        return toastMsgObserver;
    }

    public void Login() {
        final String userId = model.getUserId();
        if (userId == null) {
            toastMsgObserver.setValue("Please enter a user Id.");
            return;
        }
        final String password = model.getPassword();
        if (password == null) {
            toastMsgObserver.setValue("Please enter password.");
            return;
        }
        //String encryptedPassword = Utils.md5Encryption(userId + Utils.md5Encryption(password));
        String encryptedPassword = "3229c1097c00d497a0fd282d586be050";
        OnBoardingResponse onBoardingResponse = new OnBoardingResponse();
        onBoardingResponse.setUser_id(userId);
        onBoardingResponse.setPassword(encryptedPassword);

        Call<OnBoardingResponse> call = ApiUtils.getOnBoardingService().login(onBoardingResponse);
        call.enqueue(new Callback<OnBoardingResponse>() {
            @Override
            public void onResponse(Call<OnBoardingResponse> call, Response<OnBoardingResponse> response) {
                if (response.code() == 200) {
                    OnBoardingResponse body = response.body();
                    String msg = "Success! " + body.getName();
                    toastMsgObserver.setValue(msg);
                } else {
                    toastMsgObserver.setValue("The username or password is incorrect");
                }
            }

            @Override
            public void onFailure(Call<OnBoardingResponse> call, Throwable t) {
                toastMsgObserver.setValue("Error! please try again!" + t.getMessage());
            }
        });

    }

}
