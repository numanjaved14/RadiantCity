package com.test.radientcity.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.test.radientcity.DataModels.AnnouncementdataModel;
import com.test.radientcity.R;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.recyclerViewAdapterHolder> {

    private Context context;
    List<AnnouncementdataModel> list;

    public RecyclerViewAdapter(Context ct) {
        // this.list = list;
        context = ct;
    }

    @NonNull
    @Override
    public recyclerViewAdapterHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_row, parent, false);
        final recyclerViewAdapterHolder mViewHolder = new recyclerViewAdapterHolder(mView);

        return mViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull recyclerViewAdapterHolder holder, int position) {
        holder.announce_title.setText(list.get(position).getTitle());
        holder.announce_description.setText(list.get(position).getDescription());
        holder.tv_status.setText(list.get(position).getStatus());
        holder.tv_date.setText(list.get(position).getDate());
    }

    @Override
    public int getItemCount() {
        int size = 0;
        if (list!=null && list.size()!=0){
            size = list.size();
        }
        return size;
    }

    public class recyclerViewAdapterHolder extends RecyclerView.ViewHolder {

        TextView announce_title, announce_description,tv_date,tv_status;

        public recyclerViewAdapterHolder(@NonNull View itemView) {
            super(itemView);
            announce_title = itemView.findViewById(R.id.announce_title);
            announce_description = itemView.findViewById(R.id.announce_description);
            tv_date = itemView.findViewById(R.id.tv_date);
            tv_status = itemView.findViewById(R.id.tv_status);
        }
    }

    public void setList(List<AnnouncementdataModel> listData) {
        this.list = listData;
        notifyDataSetChanged();
    }
}

