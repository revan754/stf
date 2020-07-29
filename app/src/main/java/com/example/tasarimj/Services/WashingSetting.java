package com.example.tasarimj.Services;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WashingSetting {

    @SerializedName("DeviceId")
    @Expose
    private String deviceId;
    @SerializedName("PressureAdjust")
    @Expose
    private String pressureAdjust;
    @SerializedName("WasingDate")
    @Expose
    private String wasingDate;
    @SerializedName("WasingTime")
    @Expose
    private String wasingTime;

    public WashingSetting(String deviceId, String pressureAdjust, String wasingDate, String wasingTime) {
        this.deviceId = deviceId;
        this.pressureAdjust = pressureAdjust;
        this.wasingDate = wasingDate;
        this.wasingTime = wasingTime;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getPressureAdjust() {
        return pressureAdjust;
    }

    public void setPressureAdjust(String pressureAdjust) {
        this.pressureAdjust = pressureAdjust;
    }

    public String getWasingDate() {
        return wasingDate;
    }

    public void setWasingDate(String wasingDate) {
        this.wasingDate = wasingDate;
    }

    public String getWasingTime() {
        return wasingTime;
    }

    public void setWasingTime(String wasingTime) {
        this.wasingTime = wasingTime;
    }

}