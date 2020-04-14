package com.example.minijobmobile.onboarding.register;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.minijobmobile.base.BaseViewModel;
import com.example.minijobmobile.remote.ApiUtils;
import com.example.minijobmobile.remote.response.OnBoardingResponse;
import com.example.minijobmobile.util.Utils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterViewModel extends BaseViewModel<RegisterModel> {

    private MutableLiveData<String> toastMsgObserver = new MutableLiveData<>();

    public RegisterViewModel(RegisterModel registerModel) {
        super(registerModel);
    }

    public void setUserId(String userId) {
        model.setUserId(userId);
    }

    public void setPassword(String password) {
        model.setPassword(password);
    }

    public void setFirstName(String firstName) {
        model.setFirstName(firstName);
    }

    public void setLastName(String lastName) {
        model.setLastName(lastName);
    }

    public LiveData<String> getToastObserver() {
        return toastMsgObserver;
    }

    public void Register() {
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
        final String firstName = model.getFirstName();
        if (firstName == null) {
            toastMsgObserver.setValue("Please enter first name");
            return;
        }
        final String lastName = model.getLastName();
        if (lastName == null) {
            toastMsgObserver.setValue("Please enter last name");
            return;
        }
        String encryptedPassword = Utils.md5Encryption(password);
        OnBoardingResponse onBoardingResponse = new OnBoardingResponse();
        onBoardingResponse.setUser_id(userId);
        onBoardingResponse.setPassword(encryptedPassword);
        onBoardingResponse.setFirst_name(firstName);
        onBoardingResponse.setLast_name(lastName);

        Call<OnBoardingResponse> call = ApiUtils.getOnBoardingService().register(onBoardingResponse);
        call.enqueue(new Callback<OnBoardingResponse>() {
            @Override
            public void onResponse(Call<OnBoardingResponse> call, Response<OnBoardingResponse> response) {
                OnBoardingResponse body = response.body();
                if (body.getStatus().equals("OK")) {
                    toastMsgObserver.setValue("Register Success!");
                } else {
                    toastMsgObserver.setValue("User exist!");
                }
            }

            @Override
            public void onFailure(Call<OnBoardingResponse> call, Throwable t) {
                toastMsgObserver.setValue(t.getMessage());
            }
        });
    }
}
