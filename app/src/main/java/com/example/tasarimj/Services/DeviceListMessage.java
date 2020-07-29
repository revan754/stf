package com.example.tasarimj.Services;

import android.bluetooth.BluetoothClass;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DeviceListMessage {

    @SerializedName("Success")
    @Expose
    private Boolean success;
    @SerializedName("Devices")
    @Expose
    private List<DeviceList> devices = null;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public List<DeviceList> getDevices() {
        return devices;
    }

    public void setDevices(List<DeviceList> devices) {
        this.devices = devices;
    }

}
