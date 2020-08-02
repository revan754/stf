package com.example.hydrostat.Services;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class User1 {

    private String NameSurname, Email, PhoneNumber, Password;
    private int Id, CountryId, ProvinceId;

    @SerializedName("DeviceIds")
    @Expose
    private ArrayList<String> DeviceIds;

    public User1(int Id, String NameSurname, String Email, String PhoneNumber, int CountryId,
                 int ProvinceId, String Password, ArrayList DeviceIds) {
        this.Id = Id;
        this.NameSurname = NameSurname;
        this.Email = Email;
        this.ProvinceId = ProvinceId;
        this.PhoneNumber = PhoneNumber;
        this.CountryId = CountryId;
        this.Password = Password;
        this.DeviceIds = DeviceIds;
    }

    public ArrayList<String> getDeviceIds() {
        return DeviceIds;
    }

    public void setDeviceIds(ArrayList<String> deviceIds) {
        DeviceIds = deviceIds;
    }

    public int getProvinceId() {
        return ProvinceId;
    }

    public void setProvinceId(int provinceId) {
        ProvinceId = provinceId;
    }

    public String getNameSurname() {
        return NameSurname;
    }

    public void setNameSurname(String nameSurname) {
        NameSurname = nameSurname;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public int getCountryId() {
        return CountryId;
    }

    public void setCountryId(int countryId) {
        CountryId = countryId;
    }


}
