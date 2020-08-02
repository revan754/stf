package com.example.hydrostat.Services;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CountryResult {

    @SerializedName("_BinaryCode")
    @Expose
    private String binaryCode;
    @SerializedName("_CountryID")
    @Expose
    private Integer countryID;
    @SerializedName("_CountryName")
    @Expose
    private String countryName;
    @SerializedName("_PhoneCode")
    @Expose
    private String phoneCode;
    @SerializedName("_TripleCode")
    @Expose
    private String tripleCode;

    public String getBinaryCode() {
        return binaryCode;
    }

    public void setBinaryCode(String binaryCode) {
        this.binaryCode = binaryCode;
    }

    public Integer getCountryID() {
        return countryID;
    }

    public void setCountryID(Integer countryID) {
        this.countryID = countryID;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getPhoneCode() {
        return phoneCode;
    }

    public void setPhoneCode(String phoneCode) {
        this.phoneCode = phoneCode;
    }

    public String getTripleCode() {
        return tripleCode;
    }

    public void setTripleCode(String tripleCode) {
        this.tripleCode = tripleCode;
    }

}