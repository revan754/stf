package com.example.tasarimj.Activites;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tasarimj.R;
import com.example.tasarimj.adapter.CommunicationProduct;
import com.example.tasarimj.adapter.CommunicationAdapter;

public class UserActivity extends AppCompatActivity implements View.OnClickListener {

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgetpassword);

    }

    /**
     * This implemented method is to listen the click on view
     *
     * @param v
     */
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.parentTitle) {
            Intent parentTitle = new Intent(getApplicationContext(), LoginActivity.class);
            parentTitle.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(parentTitle);
        }
    }
}