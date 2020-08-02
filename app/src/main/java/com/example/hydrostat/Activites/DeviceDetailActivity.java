package com.example.hydrostat.Activites;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;
import android.view.ContextThemeWrapper;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.hydrostat.Api.APIClient;
import com.example.hydrostat.R;
import com.example.hydrostat.Services.DeviceIdInfo;
import com.example.hydrostat.Services.DeviceIdInfoMessage;
import com.example.hydrostat.Services.DeviceIdUserId;
import com.example.hydrostat.Services.User;
import com.example.hydrostat.Storage.SharedManager;
import com.example.hydrostat.adapter.NotificationProduct;
import com.example.hydrostat.adapter.NotificationAdapter;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DeviceDetailActivity extends AppCompatActivity implements View.OnClickListener  {

    private TextView battary, filterSetting, inPressure, outPressure, workingTime, lastWashingTime, sarjDurumu, cihazNo;
    private String position, DeviceId;
    private User user;
    private ImageView setting;
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
        setting.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {


               Context wrapper = new ContextThemeWrapper(DeviceDetailActivity.this, R.style.PopupMenu);
                PopupMenu pm = new PopupMenu(wrapper, v);

                pm.inflate(R.menu.menu);

                pm.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()){


                            case R.id.kAyarlari:
                                Toast.makeText(DeviceDetailActivity.this, "Clicked Second Menu Item", Toast.LENGTH_SHORT).show();
                                return true;

                            case R.id.cAyarlari:
                                Toast.makeText(DeviceDetailActivity.this, "Clicked Third Menu Item", Toast.LENGTH_SHORT).show();
                                return true;

                            case R.id.kCikis:
                                SharedManager.getInstance(DeviceDetailActivity.this).clear();
                                Intent login = new Intent(DeviceDetailActivity.this, LoginActivity.class);
                                login.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(login);
                                return true;
                        }

                        return true;
                    }
                });

                try {
                    Field[] fields = pm.getClass().getDeclaredFields();

                    for (Field field : fields) {
                        if ("mPopup".equals(field.getName())) {
                            field.setAccessible(true);
                            Object menuPopupHelper = field.get(pm);
                            Class<?> classPopupHelper = Class.forName(menuPopupHelper
                                    .getClass().getName());
                            Method setForceIcons = classPopupHelper.getMethod(
                                    "setForceShowIcon", boolean.class);
                            setForceIcons.invoke(menuPopupHelper, true);
                            break;
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
               finally {

                    pm.show();

                }
            }



        });

    }


    public void inits() {
        user = SharedManager.getInstance(this).getUser();
       setting = findViewById(R.id.setting);
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
                    if (mDeviceList.get(0).getLastWashingTime() == null)
                    {
                        lastWashingTime.setText("Bulunmadı");

                    }
                    else
                    {
                        lastWashingTime.setText(mDeviceList.get(0).getLastWashingTime().toString());

                    }
                    if (mDeviceList.get(0).getFilterSetting() == null)
                    {
                        filterSetting.setText("Null");

                    }
                    else
                    {
                        filterSetting.setText(mDeviceList.get(0).getFilterSetting().getPressureAdjust().toString());

                    }

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

        }
    }
}