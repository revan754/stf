package com.example.tasarimj.Activites;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tasarimj.Api.APIClient;
import com.example.tasarimj.R;
import com.example.tasarimj.Services.DeviceIdInfo;
import com.example.tasarimj.Services.DeviceIdInfoMessage;
import com.example.tasarimj.Services.DeviceIdUserId;
import com.example.tasarimj.Services.User;
import com.example.tasarimj.Storage.SharedManager;
import com.example.tasarimj.adapter.NotificationProduct;
import com.example.tasarimj.adapter.NotificationAdapter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DeviceDetailActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView battary, filterSetting, inPressure, outPressure, workingTime, lastWashingTime, sarjDurumu, cihazNo;
    private String position, DeviceId;
    private User user;

    private List<DeviceIdInfo> mDeviceList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_devicedetail);


        RecyclerView recyclerView = findViewById(R.id.recycle2);
        NotificationAdapter productAdapter = new NotificationAdapter(this, NotificationProduct.getData());
        recyclerView.setAdapter(productAdapter);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        inits();
    }

    public void inits() {
        user = SharedManager.getInstance(this).getUser();

        battary = findViewById(R.id.sarjDurumu);
        filterSetting = findViewById(R.id.filtreSetting);
        inPressure = findViewById(R.id.inPressure);
        outPressure = findViewById(R.id.outPressure);
        workingTime = findViewById(R.id.workTime);
        lastWashingTime = findViewById(R.id.lastWashingTime);
        sarjDurumu = findViewById(R.id.sarjOlma);
        cihazNo = findViewById(R.id.cihazNo);
        position = getIntent().getStringExtra("pos");
        DeviceId = getIntent().getStringExtra("DeviceId");
        setTexts();

    }

    @SuppressLint("SetTextI18n")
    public void setTexts() {

        Call<DeviceIdInfoMessage> call = APIClient.getInstance().getApi().UserDeviceId(
                new DeviceIdUserId(user.getId().toString(), DeviceId));
        call.enqueue(new Callback<DeviceIdInfoMessage>() {

            @Override
            public void onResponse(Call<DeviceIdInfoMessage> call, Response<DeviceIdInfoMessage> response) {

                assert response.body() != null;
                mDeviceList = response.body().getDevices();

                if (response.isSuccessful()) {
                    cihazNo.setText(position + ""+"Numaralı Cihaz");
                    battary.setText("%"+mDeviceList.get(0).getBattery().toString());
                    inPressure.setText(mDeviceList.get(0).getInPressure().toString());
                    outPressure.setText(mDeviceList.get(0).getOutPressure().toString());
                    workingTime.setText(mDeviceList.get(0).getWorkingTime().toString());
                    lastWashingTime.setText("20.02.2020");
                            //mDeviceList.get(0).getLastWashingTime().toString());
                    filterSetting.setText("0.5");
                            //mDeviceList.get(0).getFilterSetting().getPressureAdjust().toString());

                    if (mDeviceList.get(0).getBatteryStatus() == 0) {
                        sarjDurumu.setText(DeviceDetailActivity.this.getResources().getString(R.string.sarjOlmuyor));

                    } else if (mDeviceList.get(0).getBatteryStatus() == 1) {
                        sarjDurumu.setText(DeviceDetailActivity.this.getResources().getString(R.string.sarjOluyor));

                    }
                }
            }

            @Override
            public void onFailure(Call<DeviceIdInfoMessage> call, Throwable t) {
                Toast.makeText(DeviceDetailActivity.this, getResources().getString(R.string.hataUyari), Toast.LENGTH_SHORT).show();
            }
        });


    }


    /**
     * This implemented method is to listen the click on view
     *
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.filtreSettingCard:
                Intent filterSetting = new Intent(getApplicationContext(), WashingSettingActivity.class);
                filterSetting.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                filterSetting.putExtra("DeviceId", mDeviceList.get(0).getDeviceId());
                filterSetting.putExtra("pos", position);
                startActivity(filterSetting);
                break;
            case R.id.workDetail:
                Intent workDetail = new Intent(getApplicationContext(), WorkDetailActivity.class);
                workDetail.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                workDetail.putExtra("DeviceId", mDeviceList.get(0).getDeviceId());
                workDetail.putExtra("pos", position);
                startActivity(workDetail);

                break;
            case R.id.back:
                Intent deviceList = new Intent(getApplicationContext(), DeviceListActivity.class);
                deviceList.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(deviceList);
                finish();
                break;
        }
    }
}