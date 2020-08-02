package com.example.hydrostat.Services;

public class DeviceIdUserId {
    private String UserId;
    private String DeviceId;

    public DeviceIdUserId(String UserId, String DeviceId) {
        this.UserId = UserId;
        this.DeviceId = DeviceId;
    }

    public String getDeviceId() {
        return DeviceId;
    }

    public void setDeviceId(String deviceId) {
        DeviceId = deviceId;
    }

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }
}
