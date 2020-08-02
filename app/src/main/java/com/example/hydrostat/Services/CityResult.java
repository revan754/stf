package com.example.hydrostat.Services;


import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CityResult {

    @SerializedName("CityResult")
    @Expose
    private List<Object> cityResult = null;

    public List<Object> getCityResult() {
        return cityResult;
    }

    public void setCityResult(List<Object> cityResult) {
        this.cityResult = cityResult;
    }

}