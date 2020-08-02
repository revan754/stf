package com.example.hydrostat.Activites;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.hydrostat.R;

public class WorkDetailActivity extends AppCompatActivity implements View.OnClickListener {

    private String deviceId, pos;
    private TextView deviceSetting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workdetail);
        inits();
    }

    @SuppressLint("SetTextI18n")
    public void inits() {
        deviceSetting = findViewById(R.id.cihazAyari);
        pos = getIntent().getStringExtra("pos");
        deviceId = getIntent().getStringExtra("DeviceId");
        deviceSetting.setText(pos+". "+WorkDetailActivity.this.getResources().getString(R.string.cihazAyarlari));
    }

    /**
     * This implemented method is to listen the click on view
     *
     * @param v
     */
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.parentTitle) {
            Intent parentTitle = new Intent(getApplicationContext(), DeviceDetailActivity.class);
            parentTitle.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
            parentTitle.putExtra("DeviceId", deviceId);
            parentTitle.putExtra("pos", pos);
            startActivity(parentTitle);
            finish();
        }
    }
}