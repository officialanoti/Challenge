package com.example.android.challenge.rest;

import com.example.android.challenge.FinderActivity;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Magaji on 8/30/17.
 */

public class ApiClient {
    private Retrofit retrofit;
    public ApiClient () {
        retrofit = new Retrofit.Builder()
                .baseUrl(FinderActivity.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
    public ApiInterface getApiService () {
        return retrofit.create(ApiInterface.class);
    }
}