package com.example.hydrostat.adapter;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hydrostat.Activites.DeviceDetailActivity;
import com.example.hydrostat.R;
import com.example.hydrostat.Services.DeviceList;
import com.example.hydrostat.Services.FilterSetting;


import java.util.List;

public class DeviceListAdapter extends RecyclerView.Adapter<DeviceListAdapter.MyViewHolder> implements Parcelable, View.OnClickListener {

    private List<DeviceList> mProjectList;
    private Context mContext;

    public DeviceListAdapter(Context mContext, List<DeviceList> projectList) {
        this.mContext = mContext;
        this.mProjectList = projectList;
    }


    private DeviceListAdapter(Parcel in) {

    }

    public static final Creator<DeviceListAdapter> CREATOR = new Creator<DeviceListAdapter>() {
        @Override
        public DeviceListAdapter createFromParcel(Parcel in) {
            return new DeviceListAdapter(in);
        }

        @Override
        public DeviceListAdapter[] newArray(int size) {
            return new DeviceListAdapter[size];
        }
    };



    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.itemdevice, parent, false);

        return new MyViewHolder(view);
    }


    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {

        final DeviceList list = mProjectList.get(position);
        final FilterSetting filterSetting = mProjectList.get(position).getFilterSetting();
                (holder).calismaZamani.setText(String.valueOf(mProjectList.get(position).getWorkingTime()));
      /*  if (mProjectList.get(position).getWorkingTime() != null)
        {
            (holder).ci.setText(mContext.getResources().getString(R.string.baslanmadi));
        }
        else
        {
            (holder).sonYikama.setText(String.valueOf(mProjectList.get(position).getWorkingTime()));

        }

       */
        //(holder).cihazAdi.setText(mProjectList.get(position).);
        (holder).cihazNo.setText(""+(position+1));

        (holder).calismaZamani.setText(mProjectList.get(position).getWorkingTime().toString());
        (holder).itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), DeviceDetailActivity.class);
                intent.putExtra(DeviceList.POST, list);
                intent.putExtra("DeviceId",mProjectList.get(position).getDeviceId());

                intent.putExtra("pos",(position+1)+"");
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                view.getContext().startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mProjectList.size();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {


    }

    @Override
    public void onClick(View view) {

    }


    static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView cihazNo, calismaZamani, cihazAdi;


        @Override
        public void onClick(View v) {


        }

        public MyViewHolder(View itemView) {
            super(itemView);

            cihazNo =  itemView.findViewById(R.id.cihazNo);
            cihazAdi = itemView.findViewById(R.id.cihazAdi);
            calismaZamani= itemView.findViewById(R.id.calismaSuresi);

            itemView.setOnClickListener(this);

        }

        public interface RecyclerViewClickListener {
            void onClick(View view, int position);
        }


    }
}