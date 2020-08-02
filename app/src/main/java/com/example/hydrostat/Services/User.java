package com.example.hydrostat.Services;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User {

    @SerializedName("Id")
    @Expose
    private Integer id;
    @SerializedName("RowGuid")
    @Expose
    private Object rowGuid;
    @SerializedName("NameSurname")
    @Expose
    private String nameSurname;
    @SerializedName("Email")
    @Expose
    private String email;
    @SerializedName("PhoneNumber")
    @Expose
    private String phoneNumber;
    @SerializedName("CountryId")
    @Expose
    private Integer countryId;
    @SerializedName("ProvinceId")
    @Expose
    private Integer provinceId;
    @SerializedName("Password")
    @Expose
    private String password;
    @SerializedName("Active")
    @Expose
    private Boolean active;
    @SerializedName("Payment")
    @Expose
    private Object payment;
    @SerializedName("CreateDate")
    @Expose
    private String createDate;

    @SerializedName("DeviceIds")
    @Expose
    private Object deviceIds;

    public User(Integer id, String nameSurname, String email, String phoneNumber, String password) {
        this.nameSurname = nameSurname;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Object getRowGuid() {
        return rowGuid;
    }

    public void setRowGuid(Object rowGuid) {
        this.rowGuid = rowGuid;
    }

    public String getNameSurname() {
        return nameSurname;
    }

    public void setNameSurname(String nameSurname) {
        this.nameSurname = nameSurname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Integer getCountryId() {
        return countryId;
    }

    public void setCountryId(Integer countryId) {
        this.countryId = countryId;
    }

    public Integer getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(Integer provinceId) {
        this.provinceId = provinceId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Object getPayment() {
        return payment;
    }

    public void setPayment(Object payment) {
        this.payment = payment;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }


    public Object getDeviceIds() {
        return deviceIds;
    }

    public void setDeviceIds(Object deviceIds) {
        this.deviceIds = deviceIds;
    }
}