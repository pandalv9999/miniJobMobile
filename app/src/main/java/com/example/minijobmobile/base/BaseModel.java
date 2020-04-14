package com.example.minijobmobile.base;

import com.example.minijobmobile.remote.ApiService;
import com.example.minijobmobile.remote.ApiUtils;

public abstract class BaseModel {
    protected final ApiService apiService = ApiUtils.getAPIService();
}
