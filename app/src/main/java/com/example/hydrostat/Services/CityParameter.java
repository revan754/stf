package com.example.hydrostat.Services;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CityParameter {

    @SerializedName("CountryID ")
    @Expose
    private Integer countryID;

    public Integer getCountryID() {
        return countryID;
    }

    public void setCountryID(Integer countryID) {
        this.countryID = countryID;
    }

}
