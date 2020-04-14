package com.example.minijobmobile.onboarding.register;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.minijobmobile.base.BaseViewModel;
import com.example.minijobmobile.onboarding.OnBoardingListener;
import com.example.minijobmobile.remote.ApiUtils;
import com.example.minijobmobile.remote.response.OnBoardingResponse;
import com.example.minijobmobile.util.Utils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterViewModel extends BaseViewModel<RegisterModel> {

    private OnBoardingListener onBoardingListener = null;

    public RegisterViewModel(RegisterModel registerModel) {
        super(registerModel);
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

    public void setFirstName(String firstName) {
        model.setFirstName(firstName);
    }

    public void setLastName(String lastName) {
        model.setLastName(lastName);
    }

    public void Register() {
        final String userId = model.getUserId();
        if (userId == null) {
            onBoardingListener.onFailure("Please enter a user Id.");
            return;
        }
        final String password = model.getPassword();
        if (password == null) {
            onBoardingListener.onFailure("Please enter password.");
            return;
        }
        final String firstName = model.getFirstName();
        if (firstName == null) {
            onBoardingListener.onFailure("Please enter first name");
            return;
        }
        final String lastName = model.getLastName();
        if (lastName == null) {
            onBoardingListener.onFailure("Please enter last name");
            return;
        }
        onBoardingListener.onSuccess(model.userRegister());

    }
}
