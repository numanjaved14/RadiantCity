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
import com.test.radientcity.R;

import java.util.List;

public class ServiceAdapter extends RecyclerView.Adapter<ServiceAdapter.serviceViewAdapterHolder> {

    private Context context;
    List<Datamodel_service_show> list;

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
        holder.service_title.setText(list.get(position).title);
        holder.service_date.setText(list.get(position).date);
        holder.service_time.setText(list.get(position).time);
        holder.service_acception.setText(list.get(position).acception);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class serviceViewAdapterHolder extends RecyclerView.ViewHolder {
        TextView service_title, service_date, service_time, service_acception;

        public serviceViewAdapterHolder(@NonNull View itemView) {
            super(itemView);
            service_title = itemView.findViewById(R.id.service_title);
            service_date = itemView.findViewById(R.id.service_date);
            service_time = itemView.findViewById(R.id.service_time);
            service_acception = itemView.findViewById(R.id.service_acception);
        }
    }

    public void setList(List<Datamodel_service_show> listData) {
        this.list = listData;
        notifyDataSetChanged();
    }
}
