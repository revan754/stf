package com.example.tasarimj.Activites;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.tasarimj.Api.APIClient;
import com.example.tasarimj.R;
import com.example.tasarimj.Services.LoginUser;
import com.example.tasarimj.Services.UserResponse;
import com.example.tasarimj.Storage.SharedManager;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    public EditText editPhone, editPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);
        inits();
    }

    public void inits() {
        editPhone = findViewById(R.id.edit_phonee);
        editPassword = findViewById(R.id.edit_passwordd);
    }

    @Override
    protected void onStart() {
        super.onStart();

        //----------------- kullanıcı daha önce giriş yaptıysa direct ikinci sayfa açılmaktadır
       /* if (SharedManager.getInstance(this).isLoggedIn()) {
            Intent giris = new Intent(this, DeviceListActivity.class);
            giris.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(giris);
        }

        */
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void userLogin() {

        final String tel = Objects.requireNonNull(editPhone.getText()).toString().trim();
        final String pass = Objects.requireNonNull(editPassword.getText()).toString().trim();
        if (tel.isEmpty()) {
            editPhone.setError(getResources().getString(R.string.bosAlanUyarisi));
            editPhone.requestFocus();
        } else if (pass.isEmpty()) {
            editPassword.setError(getResources().getString(R.string.bosAlanUyarisi));
            editPassword.requestFocus();
        } else {
            LoginUser loginUser = new LoginUser(tel, pass);
            Call<UserResponse> loginCall = APIClient.getInstance().getApi().Login(loginUser);
            loginCall.enqueue(new Callback<UserResponse>() {
                @Override
                public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {

                    UserResponse userResponse = response.body();
                    if (response.isSuccessful()) {

                        if (userResponse == null || userResponse.getUser() == null) {
                            editPhone.setError(getResources().getString(R.string.kullaniciSifreYanlisUyari));
                            editPhone.requestFocus();
                            return;
                        }
                        SharedManager.getInstance(LoginActivity.this)
                                .saveUser(userResponse.getUser().getId(), userResponse.getUser().getNameSurname(), userResponse.getUser().getEmail()
                                        , userResponse.getUser().getPhoneNumber(), userResponse.getUser().getPassword());
 //.saveUser(userResponse.getUser().getId(), userResponse.getUser().getNameSurname(), userResponse.getUser().getEmail()
   //                             , userResponse.getUser().getPhoneNumber(), userResponse.getUser().getPassword());

                        Intent DeviceList = new Intent(LoginActivity. this, DeviceListActivity.class);
                        startActivity(DeviceList);
                        finish();

                    } else {
                        Toast.makeText(LoginActivity.this, getResources().getString(R.string.hataUyari), Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<UserResponse> call, Throwable t) {
                    Toast.makeText(LoginActivity.this, getResources().getString(R.string.hataUyari), Toast.LENGTH_SHORT).show();
                }
            });
        }

    }

    /**
     * This implemented method is to listen the click on view
     *
     * @param v
     */
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.signUp:
                Intent signup = new Intent(getApplicationContext(), SignUpActivity.class);
                startActivity(signup);

                break;
            case R.id.forgetPassword:
                Intent password = new Intent(getApplicationContext(), UserActivity.class);
                startActivity(password);

                break;
            case R.id.login:
                userLogin();
                break;
        }
    }
}