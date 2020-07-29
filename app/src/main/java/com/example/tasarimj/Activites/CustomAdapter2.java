package com.example.tasarimj.Activites;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.tasarimj.R;
import com.example.tasarimj.adapter.ModelDevice;

import java.util.ArrayList;

public class CustomAdapter2 extends BaseAdapter {
    private Context context;
    private ArrayList<String> itemModelLatLongList;

    CustomAdapter2(Context context, ArrayList<String> modelLatLongList) {
        this.context = context;
        this.itemModelLatLongList = modelLatLongList;
    }

    @Override
    public int getCount() {
        return itemModelLatLongList.size();
    }

    @Override
    public Object getItem(int position) {
        return itemModelLatLongList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @SuppressLint({"ViewHolder", "SetTextI18n"})
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        convertView = null;
        LayoutInflater mInflater = (LayoutInflater) context
                .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        convertView = mInflater.inflate(R.layout.item4, null);
        TextView tvLon = convertView.findViewById(R.id.device);

        String m = itemModelLatLongList.get(position);

        tvLon.setText(m);


        return convertView;
    }


}
