package com.example.hydrostat.Services;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FilterSetting implements Parcelable {

    public static final String Filter = "filter";

    @SerializedName("Id")
    @Expose
    private Integer id;
    @SerializedName("RowGuid")
    @Expose
    private Object rowGuid;
    @SerializedName("DeviceId")
    @Expose
    private String deviceId;
    @SerializedName("PressureAdjust")
    @Expose
    private Double pressureAdjust;
    @SerializedName("WasingDate")
    @Expose
    private String wasingDate;
    @SerializedName("WasingTime")
    @Expose
    private String wasingTime;
    @SerializedName("Active")
    @Expose
    private Boolean active;
    @SerializedName("CreateDate")
    @Expose
    private String createDate;

    protected FilterSetting(Parcel in) {
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readInt();
        }
        deviceId = in.readString();
        if (in.readByte() == 0) {
            pressureAdjust = null;
        } else {
            pressureAdjust = in.readDouble();
        }
        wasingDate = in.readString();
        wasingTime = in.readString();
        byte tmpActive = in.readByte();
        active = tmpActive == 0 ? null : tmpActive == 1;
        createDate = in.readString();
    }

    public static final Creator<FilterSetting> CREATOR = new Creator<FilterSetting>() {
        @Override
        public FilterSetting createFromParcel(Parcel in) {
            return new FilterSetting(in);
        }

        @Override
        public FilterSetting[] newArray(int size) {
            return new FilterSetting[size];
        }
    };

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

    public Double getPressureAdjust() {
        return pressureAdjust;
    }

    public void setPressureAdjust(Double pressureAdjust) {
        this.pressureAdjust = pressureAdjust;
    }

    public String getWasingDate() {
        return wasingDate;
    }

    public void setWasingDate(String wasingDate) {
        this.wasingDate = wasingDate;
    }

    public String getWasingTime() {
        return wasingTime;
    }

    public void setWasingTime(String wasingTime) {
        this.wasingTime = wasingTime;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
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
        if (pressureAdjust == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeDouble(pressureAdjust);
        }
        dest.writeString(wasingDate);
        dest.writeString(wasingTime);
        dest.writeByte((byte) (active == null ? 0 : active ? 1 : 2));
        dest.writeString(createDate);
    }
}