package com.test.radientcity;

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

    fragment_service_add fragment_Service_Add = new fragment_service_add();

    FloatingActionButton addServiceButton;

    List<Datamodel_service_show> list = new ArrayList<Datamodel_service_show>();
    RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_service, container, false);

        list.add(new Datamodel_service_show("Title", "14-03-2020", "12:00 PM", "Yes/NO"));
        list.add(new Datamodel_service_show("Title", "14-03-2020", "12:00 PM", "Yes/NO"));
        list.add(new Datamodel_service_show("Title", "14-03-2020", "12:00 PM", "Yes/NO"));
        list.add(new Datamodel_service_show("Title", "14-03-2020", "12:00 PM", "Yes/NO"));
        list.add(new Datamodel_service_show("Title", "14-03-2020", "12:00 PM", "Yes/NO"));

        recyclerView = v.findViewById(R.id.service_show);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        ServiceAdapter serviceAdapter = new ServiceAdapter(getActivity(), list);
        recyclerView.setAdapter(serviceAdapter);

        addServiceButton = v.findViewById(R.id.add_service_btn);
        addServiceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.main_frame, fragment_Service_Add);
                fragmentTransaction.commit();
            }
        });

        return v;
    }
}