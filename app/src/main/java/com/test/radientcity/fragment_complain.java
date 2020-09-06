package com.test.radientcity;

import android.os.Bundle;
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

import androidx.fragment.app.Fragment;


public class fragment_complain extends Fragment {

    Spinner complainSpinner;
    EditText complainDescription;
    Button complainSubmit;

    String options[] = {"Bad Services", "Bad Security", "Other"}, s1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View complainView = inflater.inflate(R.layout.fragment_complain, container, false);

        complainDescription = complainView.findViewById(R.id.complain_description);
        complainSubmit = complainView.findViewById(R.id.complain_submit);
        complainSpinner = complainView.findViewById(R.id.complain_spinner);

        ArrayAdapter adapter = new ArrayAdapter(getActivity(),android.R.layout.simple_spinner_dropdown_item,options);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        complainSpinner.setAdapter(adapter);

        complainSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Object item = adapterView.getItemAtPosition(i);

                //TODO: Send the value of Complain title to API
                if (item != null) {
                    Toast.makeText(getContext(), item.toString(),
                            Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        return complainView;
    }
}