package com.example.hydrostat.Api;


import com.example.hydrostat.Services.DeviceId;
import com.example.hydrostat.Services.DeviceIdInfoMessage;
import com.example.hydrostat.Services.DeviceIdUserId;
import com.example.hydrostat.Services.DeviceListMessage;
import com.example.hydrostat.Services.LoginUser;
import com.example.hydrostat.Services.User1;
import com.example.hydrostat.Services.UserResponse;
import com.example.hydrostat.Services.UserSignUp;
import com.example.hydrostat.Services.WashingSetting;
import com.example.hydrostat.Services.WashingSettingMessage;

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
