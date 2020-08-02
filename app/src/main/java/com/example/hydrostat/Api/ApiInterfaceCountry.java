package com.example.hydrostat.Api;

import com.example.hydrostat.Services.CityParameter;
import com.example.hydrostat.Services.CityResult;
import com.example.hydrostat.Services.Country;

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
