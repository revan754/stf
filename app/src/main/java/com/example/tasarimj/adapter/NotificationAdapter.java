package com.example.tasarimj.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.tasarimj.R;

import java.util.ArrayList;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.MyViewHolder> {

    private ArrayList<NotificationProduct> mProductList;
    private LayoutInflater inflater;

    public NotificationAdapter(Context context, ArrayList<NotificationProduct> products) {
        inflater = LayoutInflater.from(context);
        this.mProductList = products;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.itemnotification, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        NotificationProduct selectedProduct = mProductList.get(position);
        holder.setData(selectedProduct, position);

    }

    @Override
    public int getItemCount() {
        return mProductList.size();
    }


    static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView title1, title2;

        MyViewHolder(View itemView) {
            super(itemView);
            title1 = itemView.findViewById(R.id.notification1);
            title2 = itemView.findViewById(R.id.notification2);

        }

        void setData(NotificationProduct selectedProduct, int position) {

            this.title1.setText(selectedProduct.getProductName());
            this.title2.setText(selectedProduct.getProductDescription());

        }


        @Override
        public void onClick(View v) {


        }


    }
}