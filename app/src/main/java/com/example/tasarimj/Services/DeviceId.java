package com.example.tasarimj.Services;

public class DeviceId {

    private String UserId;

    public DeviceId(String UserId) {
        this.UserId = UserId;
    }

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }
}
