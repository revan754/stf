package Models;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hydrostat.R;
import com.example.hydrostat.adapter.DeviceProduct;

import java.util.List;

public class DeviceAdapter extends RecyclerView.Adapter<DeviceAdapter.MyViewHolder> implements Parcelable, View.OnClickListener {

    private List<DeviceProduct> mProjectList;
    private Context mContext;
    private OnItemClickListener mListener;

    public DeviceAdapter(Context mContext, List<DeviceProduct> projectList, OnItemClickListener mListener) {
        this.mContext = mContext;
        this.mProjectList = projectList;
        this.mListener = mListener;
    }


    private DeviceAdapter(Parcel in) {
    }

    public static final Creator<DeviceAdapter> CREATOR = new Creator<DeviceAdapter>() {
        @Override
        public DeviceAdapter createFromParcel(Parcel in) {
            return new DeviceAdapter(in);
        }

        @Override
        public DeviceAdapter[] newArray(int size) {
            return new DeviceAdapter[size];
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
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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

        private TextView cihazNo, calismaZamani, sonYikamaTarihi;
        private LinearLayout linearSure;

        @Override
        public void onClick(View v) {
        }

        public MyViewHolder(View itemView) {
            super(itemView);

            cihazNo = itemView.findViewById(R.id.cihazNo);
            sonYikamaTarihi = itemView.findViewById(R.id.calismaSuresi);
            calismaZamani = itemView.findViewById(R.id.calismaSuresi);

        }
    }

    public interface OnItemClickListener {
        void onClick(int position);
    }
}
