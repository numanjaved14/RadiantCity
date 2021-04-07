package com.test.radientcity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.test.radientcity.Adapters.ServiceAdapter;
import com.test.radientcity.DataModels.Billdatamodel;
import com.test.radientcity.DataModels.Datamodel_service_show;
import com.test.radientcity.DataModels.ServiceDataModel;

import java.util.ArrayList;
import java.util.List;

public class fragment_service extends Fragment {


    FloatingActionButton addServiceButton;

    RecyclerView recyclerView;
    ServiceAdapter serviceAdapter = new ServiceAdapter(getActivity());
    private List<ServiceDataModel> list = new ArrayList<ServiceDataModel>();
    private FirebaseAuth mAuth;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_service, container, false);

        initializeViews(v);
        fetchAllservices();
        return v;
    }

    private void fetchAllservices() {
        list.clear();
        mAuth = FirebaseAuth.getInstance();

        FirebaseUser firebaseUser = mAuth.getCurrentUser();

        DatabaseReference firebaseRef = FirebaseDatabase.getInstance().getReference("Service").child(firebaseUser.getUid());
        firebaseRef.addValueEventListener(new ValueEventListener() {
                                              @Override
                                              public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                                  if (dataSnapshot.getValue() != null) {
                                                      for (DataSnapshot shots : dataSnapshot.getChildren()) {
                                                          ServiceDataModel serviceDataModel = shots.getValue(ServiceDataModel.class);
                                                          if (serviceDataModel != null) {
                                                              ServiceDataModel serviceDataModelNew = new ServiceDataModel();
                                                              serviceDataModelNew.setCategary(serviceDataModel.getCategary());
                                                              serviceDataModelNew.setDescription(serviceDataModel.getDescription());
                                                              serviceDataModelNew.setServiceDate(serviceDataModel.getServiceDate());
                                                              serviceDataModelNew.setServiceStatus(serviceDataModel.getServiceStatus());


                                                              list.add(serviceDataModelNew);

                                                          }
                                                      }
                                                      if (list.size() != 0)
                                                          serviceAdapter.setList(list);
                                                  } else {
                                                      Toast.makeText(getActivity(), "No Service Found",
                                                              Toast.LENGTH_SHORT).show();
                                                  }
                                              }

                                              @Override
                                              public void onCancelled(@NonNull DatabaseError error) {
                                                  Toast.makeText(getActivity(), error.getMessage(),
                                                          Toast.LENGTH_SHORT).show();
                                              }
                                          }
        );
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

}