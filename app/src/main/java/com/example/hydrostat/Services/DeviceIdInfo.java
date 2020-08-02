package com.example.hydrostat.Services;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DeviceIdInfo {


    @SerializedName("FilterSetting")
    @Expose
    private FilterSetting filterSetting;
    @SerializedName("Id")
    @Expose
    private Integer id;
    @SerializedName("RowGuid")
    @Expose
    private Object rowGuid;
    @SerializedName("DeviceId")
    @Expose
    private String deviceId;
    @SerializedName("Battery")
    @Expose
    private Double battery;
    @SerializedName("BatteryStatus")
    @Expose
    private Integer batteryStatus;
    @SerializedName("InPressure")
    @Expose
    private Double inPressure;
    @SerializedName("OutPressure")
    @Expose
    private Double outPressure;
    @SerializedName("WorkingTime")
    @Expose
    private Integer workingTime;
    @SerializedName("LastWashingTime")
    @Expose
    private String lastWashingTime;
    @SerializedName("UserId")
    @Expose
    private Integer userId;

    public FilterSetting getFilterSetting() {
        return filterSetting;
    }

    public void setFilterSetting(FilterSetting filterSetting) {
        this.filterSetting = filterSetting;
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

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public Double getBattery() {
        return battery;
    }

    public void setBattery(Double battery) {
        this.battery = battery;
    }

    public Integer getBatteryStatus() {
        return batteryStatus;
    }

    public void setBatteryStatus(Integer batteryStatus) {
        this.batteryStatus = batteryStatus;
    }

    public Double getInPressure() {
        return inPressure;
    }

    public void setInPressure(Double inPressure) {
        this.inPressure = inPressure;
    }

    public Double getOutPressure() {
        return outPressure;
    }

    public void setOutPressure(Double outPressure) {
        this.outPressure = outPressure;
    }

    public Integer getWorkingTime() {
        return workingTime;
    }

    public void setWorkingTime(Integer workingTime) {
        this.workingTime = workingTime;
    }

    public String getLastWashingTime() {
        return lastWashingTime;
    }

    public void setLastWashingTime(String lastWashingTime) {
        this.lastWashingTime = lastWashingTime;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

}
