package com.example.tasarimj.Services;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Country {

    @SerializedName("CountryResult")
    @Expose
    private List<CountryResult> countryResult = null;

    public List<CountryResult> getCountryResult() {
        return countryResult;
    }

    public void setCountryResult(List<CountryResult> countryResult) {
        this.countryResult = countryResult;
    }

}