package com.example.hydrostat.Services;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DeviceIdInfoMessage {

    @SerializedName("Success")
    @Expose
    private Boolean success;
    @SerializedName("Devices")
    @Expose
    private List<DeviceIdInfo> devices = null;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public List<DeviceIdInfo> getDevices() {
        return devices;
    }

    public void setDevices(List<DeviceIdInfo> devices) {
        this.devices = devices;
    }

}