package com.example.android.challenge.rest;

import com.example.android.challenge.Models.DeveloperResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Magaji on 8/30/17.
 */

public interface ApiInterface  {

    @GET("search/users?q=location:lagos+language:java")
    Call<DeveloperResponse> getDevelopers();
}
