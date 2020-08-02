package com.example.hydrostat.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.hydrostat.R;

import java.util.ArrayList;

public class CommunicationAdapter extends RecyclerView.Adapter<CommunicationAdapter.MyViewHolder> {

    private ArrayList<CommunicationProduct> mProductList;
    private LayoutInflater inflater;

    public CommunicationAdapter(Context context, ArrayList<CommunicationProduct> products) {
        inflater = LayoutInflater.from(context);
        this.mProductList = products;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.itemcommunication, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        CommunicationProduct selectedProduct = mProductList.get(position);
        holder.setData(selectedProduct, position);

    }

    @Override
    public int getItemCount() {
        return mProductList.size();
    }


    static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView tarih, fiyat;

        MyViewHolder(View itemView) {
            super(itemView);
            tarih =  itemView.findViewById(R.id.tarih);
            fiyat = itemView.findViewById(R.id.fiyat);

        }

        void setData(CommunicationProduct selectedProduct, int position) {

            this.tarih.setText(selectedProduct.getProductName());
            this.fiyat.setText(selectedProduct.getProductDescription());

        }


        @Override
        public void onClick(View v) {


        }


    }
}