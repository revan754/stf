package com.example.hydrostat.Activites;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.hydrostat.Api.APIClient;
import com.example.hydrostat.R;
import com.example.hydrostat.Services.DeviceList;
import com.example.hydrostat.Services.FilterSetting;
import com.example.hydrostat.Services.WashingSetting;
import com.example.hydrostat.Services.WashingSettingMessage;

import java.util.Calendar;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WashingSettingActivity extends AppCompatActivity implements View.OnClickListener {

    private static EditText editPressure;
    private static TextView editWashingTime, editWashingDateTime;
    private DeviceList mPost;
    private TextView cihazNo;
    private FilterSetting filterSetting;
    private String deviceId, pos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_washingsetting);
        inits();

        editWashingDateTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Şimdiki zaman bilgilerini alıyoruz. güncel yıl, güncel ay, güncel gün.
                final Calendar takvim = Calendar.getInstance();
                int yil = takvim.get(Calendar.YEAR);
                int ay = takvim.get(Calendar.MONTH);
                int gun = takvim.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dpd = new DatePickerDialog(WashingSettingActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @SuppressLint("SetTextI18n")
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                // ay değeri 0 dan başladığı için (Ocak=0, Şubat=1,..,Aralık=11)
                                // değeri 1 artırarak gösteriyoruz.
                                month += 1;

                                String sMonth = "";
                                if (month < 10) {
                                    sMonth = "0" + String.valueOf(month);
                                } else {
                                    sMonth = String.valueOf(month);
                                }
                                editWashingDateTime.setText(dayOfMonth + "." + sMonth + "." + year);
                            }
                        }, yil, ay, gun);

                dpd.setButton(DatePickerDialog.BUTTON_POSITIVE, "Seç", dpd);
                dpd.setButton(DatePickerDialog.BUTTON_NEGATIVE, "İptal", dpd);
                dpd.show();

            }
        });

        editWashingTime.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);

                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(WashingSettingActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        editWashingTime.setText(selectedHour + ":" + selectedMinute);
                    }
                }, hour, minute, true);
                mTimePicker.show();

            }
        });


    }

    public void inits() {

        editPressure = findViewById(R.id.edit_pressure_Adjustment);
        editWashingDateTime = findViewById(R.id.edit_washing_Date);
        editWashingTime = findViewById(R.id.edit_washing_Time);
        cihazNo = findViewById(R.id.cihazNo);
        Button button = findViewById(R.id.saveSetting);
        button.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v) {
                saveSetting();
            }
        });

        pos = getIntent().getStringExtra("pos");
        deviceId = getIntent().getStringExtra("DeviceId");
        cihazNo.setText(pos + ""+"Numaralı Cihaz Ayarları");

    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void saveSetting() {
        String pressure = Objects.requireNonNull(editPressure.getText().toString().trim());
        String washingTime2 = Objects.requireNonNull(editWashingDateTime.getText().toString().trim());
        String washingTime = Objects.requireNonNull(editWashingTime.getText().toString().trim());
        if (pressure.isEmpty()) {
            editPressure.setError("lütfen boş bırakmayınız");
            editPressure.requestFocus();

        } else if (washingTime2.isEmpty()) {
            editWashingDateTime.setError("lütfen boş bırakmayınız");
            editWashingDateTime.requestFocus();

        } else if (washingTime.isEmpty()) {
            editWashingTime.setError("lütfen boş bırakmayınız");
            editWashingTime.requestFocus();

        } else {

            WashingSetting washingSetting = new WashingSetting(deviceId, pressure, washingTime2, washingTime);

            final Call<WashingSettingMessage> call = APIClient.getInstance().getApi().WashingSetting(washingSetting);

            call.enqueue(new Callback<WashingSettingMessage>() {
                @Override
                public void onResponse(Call<WashingSettingMessage> call, Response<WashingSettingMessage> response) {


                    if (response.isSuccessful()) {
                        Toast.makeText(getApplicationContext(), "Kayıt Başarıyla Alındı.", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), DeviceDetailActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        intent.putExtra("DeviceId", deviceId);
                        intent.putExtra("pos", pos);
                        startActivity(intent);

                        finish();


                    } else {
                        Toast.makeText(getApplicationContext(), getResources().getString(R.string.hataUyari), Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<WashingSettingMessage> call, Throwable t) {
                    Toast.makeText(getApplicationContext(), getResources().getString(R.string.hataUyari), Toast.LENGTH_SHORT).show();
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
        if (v.getId() == R.id.parentTitle) {
            Intent detail = new Intent(getApplicationContext(), DeviceDetailActivity.class);
            detail.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
            detail.putExtra(DeviceList.POST, mPost);
            detail.putExtra(FilterSetting.Filter, filterSetting);
            startActivity(detail);
            finish();
        }
    }
}
