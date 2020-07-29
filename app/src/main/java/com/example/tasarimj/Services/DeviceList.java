package com.example.tasarimj.Services;


import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DeviceList implements Parcelable {

    public static final String POST = "Post";

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

    protected DeviceList(Parcel in) {
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readInt();
        }
        deviceId = in.readString();
        if (in.readByte() == 0) {
            battery = null;
        } else {
            battery = in.readDouble();
        }
        if (in.readByte() == 0) {
            batteryStatus = null;
        } else {
            batteryStatus = in.readInt();
        }
        if (in.readByte() == 0) {
            inPressure = null;
        } else {
            inPressure = in.readDouble();
        }
        if (in.readByte() == 0) {
            outPressure = null;
        } else {
            outPressure = in.readDouble();
        }
        if (in.readByte() == 0) {
            workingTime = null;
        } else {
            workingTime = in.readInt();
        }
        lastWashingTime = in.readString();
        if (in.readByte() == 0) {
            userId = null;
        } else {
            userId = in.readInt();
        }
    }

    public static final Creator<DeviceList> CREATOR = new Creator<DeviceList>() {
        @Override
        public DeviceList createFromParcel(Parcel in) {
            return new DeviceList(in);
        }

        @Override
        public DeviceList[] newArray(int size) {
            return new DeviceList[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (id == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(id);
        }
        dest.writeString(deviceId);
        if (battery == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeDouble(battery);
        }
        if (batteryStatus == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(batteryStatus);
        }
        if (inPressure == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeDouble(inPressure);
        }
        if (outPressure == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeDouble(outPressure);
        }
        if (workingTime == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(workingTime);
        }
        dest.writeString(lastWashingTime);
        if (userId == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(userId);
        }
    }
}