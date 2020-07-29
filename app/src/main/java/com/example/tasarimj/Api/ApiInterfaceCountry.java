package com.example.tasarimj.Api;

import com.example.tasarimj.Services.CityParameter;
import com.example.tasarimj.Services.CityResult;
import com.example.tasarimj.Services.Country;

import retrofit2.Call;

import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ApiInterfaceCountry {

    @Headers("Content-Type: application/json")
    @POST("Country")
    Call<Country> getCountry();

    @Headers("Content-Type: application/json")
    @POST("City")
    Call<CityResult> getCity(@Body CityParameter cityParameter);

}
