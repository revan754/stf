package com.example.tasarimj.Storage;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;

import com.example.tasarimj.Services.User;

public class SharedManager {

    private static final String SHARED_PREF_NAME = "my_shared";
    @SuppressLint("StaticFieldLeak")
    private static SharedManager mInstance;
    private Context mCtx;

    private SharedManager(Context mCtx) {
        this.mCtx = mCtx;

    }

    public static synchronized SharedManager getInstance(Context mCtx) {

        if (mInstance == null) {
            mInstance = new SharedManager(mCtx);
        }
        return mInstance;

    }

    public void saveUser(Integer id, String nameSurname, String email, String phoneNumber, String password) {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putInt("Id", id);
        editor.putString("NameSurname", nameSurname);
        editor.putString("Email", email);
        editor.putString("PhoneNumber", phoneNumber);
        editor.putString("Password", password);

        editor.apply();
    }


    //------------------- kullanıcı daha önce giriş yapmış ise id -1 dir
    public boolean isLoggedIn() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getInt("Id", -1) != -1;
    }

    public User getUser() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return new User(
                sharedPreferences.getInt("Id", -1),
                sharedPreferences.getString("NameSurname", null),
                sharedPreferences.getString("Email", null),
                sharedPreferences.getString("PhoneNumber", null),
                sharedPreferences.getString("Password", null)

        );
    }

    //------------------- çıkış yapmak için
    public void clear() {

        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }

}
