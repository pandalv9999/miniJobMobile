package com.example.minijobmobile.onboarding.login;

import com.example.minijobmobile.base.BaseViewModel;

import com.example.minijobmobile.remote.RemoteRequestListener;
import com.example.minijobmobile.remote.response.OnBoardingResponse;
import com.example.minijobmobile.util.Config;


public class LoginViewModel extends BaseViewModel<LoginModel> {

    private RemoteRequestListener remoteRequestListener = null;

    public LoginViewModel(LoginModel loginModel) {
        super(loginModel);
    }

    public void setRemoteRequestListener(RemoteRequestListener remoteRequestListener) {
        this.remoteRequestListener = remoteRequestListener;
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
            remoteRequestListener.onFailure("Please enter user ID");
            return;
        }
        final String password = model.getPassword();
        if (password == null) {
            remoteRequestListener.onFailure("Please enter password.");
            return;
        }
        remoteRequestListener.onSuccess(model.userLogin());
    }

}
