package com.example.tasarimj.Activites;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tasarimj.Api.APIClient;
import com.example.tasarimj.R;
import com.example.tasarimj.Services.User1;
import com.example.tasarimj.Services.UserSignUp;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddDeviceActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText deviceId;
    private ArrayList<String> itemModelDevice, item2;
    private CustomAdapter2 customAdapter;
    private ListView listView;
    private List<String> deviceList;
    private String name, email, phone, country, province, password;
    private TextView textName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adddevice);
        inits();
    }

    public void inits() {
        deviceId = findViewById(R.id.deviceIdd);
        textName = findViewById(R.id.name);
        listView = findViewById(R.id.deviceList);

        itemModelDevice = new ArrayList<>();
        customAdapter = new CustomAdapter2(getApplicationContext(), itemModelDevice);
        listView.setEmptyView(findViewById(R.id.deviceList));
        listView.setAdapter(customAdapter);
        name = getIntent().getStringExtra("name");
        email = getIntent().getStringExtra("email");
        phone = getIntent().getStringExtra("phone");
        country = getIntent().getStringExtra("country");
        province = getIntent().getStringExtra("province");
        password = getIntent().getStringExtra("password");
        textName.setText(name);

    }

    public void completeSignUp() {
        StringBuilder devices = new StringBuilder();

        if (itemModelDevice.size() < 1) {
            Toast.makeText(this, getResources().getString(R.string.cihazGirinUyari), Toast.LENGTH_LONG).show();
        } else {

            item2 = itemModelDevice;
            Call<UserSignUp> call = APIClient.getInstance().getApi().userSignUp(
                    new User1(0, name, email,
                            phone, 0, 0, password, itemModelDevice));
            call.enqueue(new Callback<UserSignUp>() {

                @Override
                public void onResponse(Call<UserSignUp> call, Response<UserSignUp> response) {

                    UserSignUp userResponse = response.body();

                    if (response.isSuccessful()) {

                        if (userResponse.getSuccess().toString().equals("true")) {
                            Intent intent = new Intent(AddDeviceActivity.this, LoginActivity.class);
                            startActivity(intent);
                            finish();
                        } else if (userResponse.getSuccess().toString().equals("false")) {
                            Toast.makeText(AddDeviceActivity.this, userResponse.getMessage(), Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(AddDeviceActivity.this, SignUpActivity.class);
                            startActivity(intent);
                            finish();

                        }

                    } else {
                        Toast.makeText(AddDeviceActivity.this, getResources().getString(R.string.hataUyari),
                                Toast.LENGTH_LONG).show();

                    }
                }

                @Override
                public void onFailure(Call<UserSignUp> call, Throwable t) {
                    Toast.makeText(AddDeviceActivity.this, getResources().getString(R.string.hataUyari), Toast.LENGTH_SHORT).show();
                }
            });

            itemModelDevice.clear();
            listView.clearFocus();
        }
    }

    public void addDevice() {
        String device = deviceId.getText().toString();
        if (device.isEmpty()) {
            Toast.makeText(getApplicationContext(), R.string.cihazGirinUyari,
                    Toast.LENGTH_SHORT).show();
        } else {

            itemModelDevice.add(device);
            customAdapter.notifyDataSetChanged();
            deviceId.setText("");


        }

    }

    /**
     * This implemented method is to listen the click on view
     *
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.complateSignUp:
                completeSignUp();
                break;
            case R.id.addDevice:

                addDevice();
        }
    }
}
