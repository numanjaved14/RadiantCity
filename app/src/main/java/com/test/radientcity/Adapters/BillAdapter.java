package com.test.radientcity.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.test.radientcity.DataModels.Billdatamodel;
import com.test.radientcity.DataModels.Datamodel_announce;
import com.test.radientcity.R;

import java.util.List;

public class BillAdapter extends RecyclerView.Adapter<BillAdapter.recyclerViewAdapterHolder> {

    private Context context;
    List<Billdatamodel> list;

    public BillAdapter(Context ct) {
        // this.list = list;
        context = ct;
    }

    @NonNull
    @Override
    public recyclerViewAdapterHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_bill, parent, false);
        final recyclerViewAdapterHolder mViewHolder = new recyclerViewAdapterHolder(mView);

        return mViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull recyclerViewAdapterHolder holder, int position) {
        holder.billed_title.setText(list.get(position).getTitle());
        holder.billDescription.setText(list.get(position).getDescription());
        holder.billCost .setText(list.get(position).getAmount());
        holder.issuedate .setText(list.get(position).getIssueDate());
        holder.dueDate.setText(list.get(position).getDueDate());
        holder.tv_status.setText(list.get(position).getStatus());
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

        TextView billed_title, billDescription, billCost, issuedate, dueDate,tv_status;

        public recyclerViewAdapterHolder(@NonNull View itemView) {
            super(itemView);
            billed_title = itemView.findViewById(R.id.billed_title);
            billDescription = itemView.findViewById(R.id.bill_description);
            billCost = itemView.findViewById(R.id.bill_cost);
            issuedate = itemView.findViewById(R.id.issuedate);
            dueDate = itemView.findViewById(R.id.duedate);
            tv_status = itemView.findViewById(R.id.tv_status);
        }
    }

    public void setList(List<Billdatamodel> listData) {
        this.list = listData;
        notifyDataSetChanged();
    }
}

