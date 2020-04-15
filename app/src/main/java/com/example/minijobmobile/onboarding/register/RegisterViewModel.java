package com.example.minijobmobile.onboarding.register;

import com.example.minijobmobile.base.BaseViewModel;
import com.example.minijobmobile.remote.RemoteRequestListener;

public class RegisterViewModel extends BaseViewModel<RegisterModel> {

    private RemoteRequestListener remoteRequestListener = null;

    public RegisterViewModel(RegisterModel registerModel) {
        super(registerModel);
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

    public void setFirstName(String firstName) {
        model.setFirstName(firstName);
    }

    public void setLastName(String lastName) {
        model.setLastName(lastName);
    }

    public void Register() {
        final String userId = model.getUserId();
        if (userId == null) {
            remoteRequestListener.onFailure("Please enter a user Id.");
            return;
        }
        final String password = model.getPassword();
        if (password == null) {
            remoteRequestListener.onFailure("Please enter password.");
            return;
        }
        final String firstName = model.getFirstName();
        if (firstName == null) {
            remoteRequestListener.onFailure("Please enter first name");
            return;
        }
        final String lastName = model.getLastName();
        if (lastName == null) {
            remoteRequestListener.onFailure("Please enter last name");
            return;
        }
        remoteRequestListener.onSuccess(model.userRegister());

    }
}
