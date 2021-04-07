package com.test.radientcity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleService;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;


public class fragment_complain extends Fragment {

    Spinner complainSpinner;
    EditText complainDescription;
    Button complainSubmit;
    String cat;
    ProgressDialog progressDialog;
    DatabaseReference firebaseRef;


    List<String> list = new ArrayList<String>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View complainView = inflater.inflate(R.layout.fragment_complain, container, false);
list.add("Bad Services");
list.add("Bad Security");
list.add("Other");
         complainDescription = complainView.findViewById(R.id.complain_description);
        complainSubmit = complainView.findViewById(R.id.complain_submit);
        complainSpinner = complainView.findViewById(R.id.complain_spinner);

        ArrayAdapter adapter = new ArrayAdapter(getActivity(),android.R.layout.simple_spinner_dropdown_item,list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        complainSpinner.setAdapter(adapter);

        complainSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                cat = list.get(i).trim();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setTitle("Adding announcement");
        progressDialog.setMessage("loading . . . . ");
        complainSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addComplaint();

            }
        });
        return complainView;
    }
    private void addComplaint() {
        progressDialog.show();
        Random rand = new Random();
        int upperbound = 15000;
        int int_random = rand.nextInt(upperbound);
        String complaintId = "complaint-" + int_random + "-Radient";
        firebaseRef = FirebaseDatabase.getInstance().getReference("Complaint").child(complaintId);
        HashMap<String, String> hashmap = new HashMap<>();
        hashmap.put("firebaseId", complaintId);
        hashmap.put("categary", cat);
        hashmap.put("description", complainDescription.getText().toString().trim());

        Log.i("PYC_LOG", "Sending Params " + hashmap);
        firebaseRef.setValue(hashmap).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                progressDialog.dismiss();
                if (task.isSuccessful()) {
                    Toast.makeText(getActivity(), "Complaint Added Successfully",
                            Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(getActivity(), task.getException().getMessage(),
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}