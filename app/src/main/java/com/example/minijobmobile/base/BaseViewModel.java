package com.example.minijobmobile.base;
import androidx.lifecycle.ViewModel;

public abstract class BaseViewModel<M extends BaseModel> extends ViewModel {
    protected final M model;

    protected BaseViewModel(M baseModel) {
        model = baseModel;
    }
}
