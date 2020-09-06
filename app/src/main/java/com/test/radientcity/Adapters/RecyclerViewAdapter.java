package com.test.radientcity.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.test.radientcity.DataModels.Datamodel_announce;
import com.test.radientcity.R;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.recyclerViewAdapterHolder> {

    private Context context;
    List<Datamodel_announce> list;

    public RecyclerViewAdapter(Context ct, List<Datamodel_announce> list){
        this.list = list;
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
        holder.announce_title.setText(list.get(position).name);
        holder.announce_description.setText(list.get(position).desc);
        holder.announce_image.setImageResource(list.get(position).imageData);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class recyclerViewAdapterHolder extends RecyclerView.ViewHolder {

        TextView announce_title, announce_description;
        ImageView announce_image;

        public recyclerViewAdapterHolder(@NonNull View itemView) {
            super(itemView);
            announce_title = itemView.findViewById(R.id.announce_title);
            announce_description = itemView.findViewById(R.id.announce_description);
            announce_image = itemView.findViewById(R.id.announce_image);
        }
    }
}
