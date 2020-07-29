package com.example.tasarimj.Api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIClient {

    private static APIClient mIntance;
    private static Retrofit retrofit;
    private static final String BASE_URL = "http://stf.aeyazilim.com/api/";
            //"http://stf.kirbag.com/api/";

    private APIClient() {
        if (retrofit == null) {
            retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
    }

    public static synchronized APIClient getInstance() {
        if (mIntance == null) {
            mIntance = new APIClient();
        }
        return mIntance;
    }

    public ApiInterface getApi() {
        return retrofit.create(ApiInterface.class);
    }
}