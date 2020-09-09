package com.test.radientcity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.test.radientcity.Adapters.ServiceAdapter;
import com.test.radientcity.DataModels.Datamodel_service_show;

import java.util.ArrayList;
import java.util.List;

public class fragment_service extends Fragment {


    FloatingActionButton addServiceButton;

    RecyclerView recyclerView;
    ServiceAdapter serviceAdapter = new ServiceAdapter(getActivity());


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_service, container, false);

        initializeViews(v);
        getData();

        return v;
    }

    private void initializeViews(View v) {
        recyclerView = v.findViewById(R.id.service_show);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        recyclerView.setAdapter(serviceAdapter);

        addServiceButton = v.findViewById(R.id.add_service_btn);
        addServiceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ServiceAdd.class);
                startActivity(intent);
            }
        });
    }

    private void getData() {
        List<Datamodel_service_show> list = new ArrayList<Datamodel_service_show>();

        list.add(new Datamodel_service_show("Title", "14-03-2020", "12:00 PM", "Yes/NO"));
        list.add(new Datamodel_service_show("Title", "14-03-2020", "12:00 PM", "Yes/NO"));
        list.add(new Datamodel_service_show("Title", "14-03-2020", "12:00 PM", "Yes/NO"));
        list.add(new Datamodel_service_show("Title", "14-03-2020", "12:00 PM", "Yes/NO"));
        list.add(new Datamodel_service_show("Title", "14-03-2020", "12:00 PM", "Yes/NO"));

        serviceAdapter.setList(list);
    }
}