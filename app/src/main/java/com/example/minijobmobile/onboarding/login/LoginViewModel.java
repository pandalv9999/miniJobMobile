package com.example.minijobmobile.onboarding.login;


import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.minijobmobile.base.BaseViewModel;
import com.example.minijobmobile.util.Utils;

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
        String encryptedPassword = Utils.md5Encryption(userId + Utils.md5Encryption(password));
        toastMsgObserver.setValue("id: " + userId + " password: " + encryptedPassword);
    }

}
