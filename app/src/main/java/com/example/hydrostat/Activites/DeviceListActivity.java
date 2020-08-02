package com.example.hydrostat.Activites;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hydrostat.Api.APIClient;
import com.example.hydrostat.R;
import com.example.hydrostat.Services.DeviceId;
import com.example.hydrostat.Services.DeviceList;
import com.example.hydrostat.Services.DeviceListMessage;
import com.example.hydrostat.Services.User;
import com.example.hydrostat.Storage.SharedManager;
import com.example.hydrostat.adapter.DeviceListAdapter;
import com.example.hydrostat.adapter.NotificationProduct;
import com.example.hydrostat.adapter.NotificationAdapter;


import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DeviceListActivity extends AppCompatActivity implements View.OnClickListener {

    private RecyclerView recyclerView, recyclerView2;
    private List<DeviceList> projectList;
    private DeviceListAdapter DeviceListAdapter;
    private User user;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_devicelist);

        initViews();


    }


    public void showMenu() {
        final Dialog dialog = new Dialog(DeviceListActivity.this);
        dialog.setTitle("Input Box");
        dialog.setContentView(R.layout.menudialog);

        final TextView signUp = dialog.findViewById(R.id.signUpCikis);
        final TextView sifreDegistir = dialog.findViewById(R.id.passwordChange);

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedManager.getInstance(DeviceListActivity.this).clear();
                Intent login = new Intent(DeviceListActivity.this, LoginActivity.class);
                login.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(login);
                dialog.dismiss();
            }
        });
        dialog.show();


    }

    @SuppressLint("WrongConstant")
    private void initViews() {
        recyclerView = findViewById(R.id.recycle1);
        NotificationAdapter productAdapter = new NotificationAdapter(this, NotificationProduct.getData());
        recyclerView.setAdapter(productAdapter);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        recyclerView2 = findViewById(R.id.recycle2);


        LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView2.setLayoutManager(linearLayoutManager2);
        user = SharedManager.getInstance(this).getUser();
        getProjectList();



    }

    public void refreshData() {
        getProjectList();
    }

    public void getProjectList() {

        DeviceId deviceId = new DeviceId(user.getId().toString());
        Call<DeviceListMessage> call = APIClient.getInstance().getApi().DevicId(deviceId);
        call.enqueue(new Callback<DeviceListMessage>() {
            @Override
            public void onResponse(Call<DeviceListMessage> call, Response<DeviceListMessage> response) {

                if (response.isSuccessful()) {
                    assert response.body() != null;
                    projectList = response.body().getDevices();
                    DeviceListAdapter = new DeviceListAdapter(DeviceListActivity.this, projectList);
                    recyclerView2.setAdapter(DeviceListAdapter);
                } else {
                    Toast.makeText(getApplicationContext(), getResources().getString(R.string.hataUyari), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<DeviceListMessage> call, Throwable t) {
                Toast.makeText(getApplicationContext(), getResources().getString(R.string.hataUyari), Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {

        }
    }
}
