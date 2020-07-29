package com.example.tasarimj.Api;


import com.example.tasarimj.Services.Country;
import com.example.tasarimj.Services.DeviceId;
import com.example.tasarimj.Services.DeviceIdInfoMessage;
import com.example.tasarimj.Services.DeviceIdUserId;
import com.example.tasarimj.Services.DeviceList;
import com.example.tasarimj.Services.DeviceListMessage;
import com.example.tasarimj.Services.LoginUser;
import com.example.tasarimj.Services.User1;
import com.example.tasarimj.Services.UserResponse;
import com.example.tasarimj.Services.UserSignUp;
import com.example.tasarimj.Services.WashingSetting;
import com.example.tasarimj.Services.WashingSettingMessage;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ApiInterface {

    @Headers("Content-Type: application/json")
    @POST("UserAdd")
    Call<UserSignUp> userSignUp(@Body User1 user);


    @Headers("Content-Type: application/json")
    @POST("Login")
    Call<UserResponse> Login(@Body LoginUser loginUser);

    @Headers("Content-Type: application/json")
    @POST("DeviceList")
    Call<DeviceListMessage> DevicId(@Body DeviceId deviceId);

    @Headers("Content-Type: application/json")
    @POST("DeviceList")
    Call<DeviceIdInfoMessage> UserDeviceId(@Body DeviceIdUserId deviceIdUserId);

    @Headers("Content-Type: application/json")
    @POST("WashingSetting")
    Call<WashingSettingMessage> WashingSetting(@Body WashingSetting washingSetting);


}
