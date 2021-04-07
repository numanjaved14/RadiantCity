package com.test.radientcity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.test.radientcity.Adapters.BillAdapter;
import com.test.radientcity.DataModels.Billdatamodel;

import java.util.ArrayList;
import java.util.List;


public class fragment_bill extends Fragment {


    private RecyclerView recycler_view;
    private List<Billdatamodel> billdatamodelList = new ArrayList<Billdatamodel>();
    private FirebaseAuth mAuth;
    private BillAdapter billAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View billView = inflater.inflate(R.layout.fragment_bill_new, container, false);


        setAdapter(billView);
        fetchAllBills();
        return billView;
    }

    private void fetchAllBills() {
        billdatamodelList.clear();
        mAuth = FirebaseAuth.getInstance();

        FirebaseUser firebaseUser= mAuth.getCurrentUser();

        DatabaseReference firebaseRef = FirebaseDatabase.getInstance().getReference("Bills").child(firebaseUser.getUid());
        firebaseRef.addValueEventListener(new ValueEventListener() {
                                              @Override
                                              public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                                  if (dataSnapshot.getValue() != null) {
                                                      for (DataSnapshot shots : dataSnapshot.getChildren()) {
                                                          Billdatamodel billdatamodel = shots.getValue(Billdatamodel.class);
                                                          if (billdatamodel!=null) {
                                                              Billdatamodel billdatamodelNew = new Billdatamodel();
                                                              billdatamodelNew.setTitle(billdatamodel.getTitle());
                                                              billdatamodelNew.setAmount(billdatamodel.getAmount());
                                                              billdatamodelNew.setDescription(billdatamodel.getDescription());
                                                              billdatamodelNew.setIssueDate(billdatamodel.getIssueDate());
                                                              billdatamodelNew.setDueDate(billdatamodel.getDueDate());
                                                              billdatamodelNew.setStatus(billdatamodel.getStatus());

                                                              billdatamodelList.add(billdatamodelNew);

                                                          }
                                                      }
                                                      if (billdatamodelList.size()!=0)
                                                      billAdapter.setList(billdatamodelList);

                                                  } else {
                                                      Toast.makeText(getActivity(), "No User Found",
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
    private void setAdapter(View billView) {
        recycler_view = billView.findViewById(R.id.recycler_view);
        recycler_view.setLayoutManager(new LinearLayoutManager(getActivity()));
          billAdapter = new BillAdapter(getActivity());
        recycler_view.setAdapter(billAdapter);

    }


}