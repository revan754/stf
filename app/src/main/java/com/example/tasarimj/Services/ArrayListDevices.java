package com.example.tasarimj.Services;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ArrayListDevices {

    @SerializedName("DeviceIds")
    @Expose
    private ArrayList<String> DeviceIds;
    public ArrayListDevices(ArrayList<String> DeviceIds) {
        this.DeviceIds=DeviceIds;
    }
}
