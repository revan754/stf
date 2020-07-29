package com.example.tasarimj.Services;

public class LoginUser {
    private String PhoneNumber, Password;

    public LoginUser(String PhoneNumber, String Password) {
        this.PhoneNumber = PhoneNumber;
        this.Password = Password;
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
}
