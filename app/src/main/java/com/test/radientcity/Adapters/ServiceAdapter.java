package com.test.radientcity.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.test.radientcity.DataModels.Datamodel_announce;
import com.test.radientcity.DataModels.Datamodel_service_show;
import com.test.radientcity.DataModels.ServiceDataModel;
import com.test.radientcity.R;

import java.util.List;

public class ServiceAdapter extends RecyclerView.Adapter<ServiceAdapter.serviceViewAdapterHolder> {

    private Context context;
    List<ServiceDataModel> list;

    public  ServiceAdapter(Context ct){
        context = ct;
    }

    @NonNull
    @Override
    public serviceViewAdapterHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.service_row, parent, false);
        final serviceViewAdapterHolder vHolder = new serviceViewAdapterHolder(v);
        return vHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull serviceViewAdapterHolder holder, int position) {
        holder.service_title.setText(list.get(position).getCategary());
        holder.service_date.setText(list.get(position).getServiceDate());
        holder.service_desc.setText(list.get(position).getDescription());
        holder.service_status.setText(list.get(position).getServiceStatus());

    }

    @Override
    public int getItemCount() {
        int size = 0;
        if (list!=null && list.size()!=0){
            size = list.size();
        }
        return size;
    }

    public class serviceViewAdapterHolder extends RecyclerView.ViewHolder {
        TextView service_title, service_date, service_desc, service_status;

        public serviceViewAdapterHolder(@NonNull View itemView) {
            super(itemView);
            service_title = itemView.findViewById(R.id.service_title);
            service_date = itemView.findViewById(R.id.service_date);
            service_desc = itemView.findViewById(R.id.service_desc);
            service_status = itemView.findViewById(R.id.service_status);

        }
    }

    public void setList(List<ServiceDataModel> listData) {
        this.list = listData;
        notifyDataSetChanged();
    }
}
