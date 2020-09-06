package com.test.radientcity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;


public class fragment_bill extends Fragment {

    TextView billedUserName, billDescription, billCost, netAmount, dueDate;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View billView = inflater.inflate(R.layout.fragment_bill, container, false);

        billedUserName = billView.findViewById(R.id.billed_user_name);
        billDescription = billView.findViewById(R.id.bill_description);
        billCost = billView.findViewById(R.id.bill_cost);
        netAmount = billView.findViewById(R.id.netamount);
        dueDate = billView.findViewById(R.id.duedate);

        return billView;
    }


}