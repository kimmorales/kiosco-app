package com.example.kioscoapp.ApiRest;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestAdapter {

    public ApiService getClientService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.URL_BASE_MONEY_CENTER)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(ApiService.class);
    }

}
