package com.example.tasarimj.Api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient2 {

    private static ApiClient2 mIntance;
    private static Retrofit retrofit;
    private static final String BASE_URL = "http://service.aeyazilim.com/Genel/AllService.svc/";

    private ApiClient2() {
        if (retrofit == null) {
            retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
    }

    public static synchronized ApiClient2 getInstance() {
        if (mIntance == null) {
            mIntance = new ApiClient2();
        }
        return mIntance;
    }

    public ApiInterfaceCountry getApi() {
        return retrofit.create(ApiInterfaceCountry.class);
    }
}