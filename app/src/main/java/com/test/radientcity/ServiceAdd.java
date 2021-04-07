package com.test.radientcity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class ServiceAdd extends AppCompatActivity {

    Spinner chooseService;
    TextView serviceDescription, datePicker, tv_timePicker;
    Button serviceRequest;
    Date date;
    SimpleDateFormat f12hours;
    DatePickerDialog dialog;
    String cat;
    private FirebaseAuth mAuth;

    List<String> list = new ArrayList<>();
    String ServiceDate, ServiceTime;
    int hour, minute;
    ProgressDialog progressDialog;
    DatabaseReference firebaseRef;


    private DatePickerDialog.OnDateSetListener dateSetListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_add);

        initialize();
        serviceSpinner();
        datePickerDialog();
        timePickerDialog();
        serviceRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 setRequest(cat, serviceDescription.getText().toString().trim(),ServiceDate,ServiceTime);
            }
        });

    }


    private void setRequest(String categary, String description, String servicedate, String servicetime) {
        progressDialog.show();
        Random rand = new Random();
        int upperbound = 15000;
        int int_random = rand.nextInt(upperbound);
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser firebaseUser= mAuth.getCurrentUser();

        String announcementId = "service-" + categary + "-" + int_random + "-Radient";
        firebaseRef = FirebaseDatabase.getInstance().getReference("Service").child(firebaseUser.getUid()).child(announcementId);
        HashMap<String, String> hashmap = new HashMap<>();
        hashmap.put("firebaseId", announcementId);
        hashmap.put("categary", categary);
        hashmap.put("description", description);
        hashmap.put("serviceDate", servicedate);
        hashmap.put("serviceTime", servicetime);
        hashmap.put("serviceStatus", "pending");

        Log.i("PYC_LOG", "Sending Params " + hashmap);
        firebaseRef.setValue(hashmap).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                progressDialog.dismiss();
                if (task.isSuccessful()) {
                    Toast.makeText(ServiceAdd.this, "Service Requested Successfully",
                            Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(ServiceAdd.this, task.getException().getMessage(),
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void timePickerDialog() {
        tv_timePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(
                        ServiceAdd.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker timePicker, int hourOfDay, int minutes) {
                                hour = hourOfDay;
                                minute = minutes;
                                ServiceTime = hour + ":" + minute;

                                SimpleDateFormat f24hours = new SimpleDateFormat(
                                        "HH:mm"
                                );
                                try {
                                    date = f24hours.parse(ServiceTime);
                                    f12hours = new SimpleDateFormat(
                                            "hh:mm aa"
                                    );
                                    ServiceTime = f12hours.format(date);
                                    tv_timePicker.setText(f12hours.format(date));
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }
                            }
                        }, 12, 0, false
                );
                timePickerDialog.getWindow().setBackgroundDrawable(
                        new ColorDrawable(Color.TRANSPARENT));
                timePickerDialog.updateTime(hour, minute);
                timePickerDialog.show();
            }
        });
    }

    private void datePickerDialog() {
        datePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                dialog = new DatePickerDialog(
                        ServiceAdd.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        dateSetListener,
                        year, month, day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker date_Picker, int year, int month, int day) {
                month = month + 1;
                //TODO: Send Service Date to API
                Log.w("onDateSet dd/mm/yy", day + "/" + month + "/" + year);
                ServiceDate = day + "/" + month + "/" + year;
                dialog.updateDate(year, month, day);
                datePicker.setText(ServiceDate);
            }
        };
    }

    private void serviceSpinner() {
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        chooseService.setAdapter(adapter);

        chooseService.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                  cat = list.get(i).trim();


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void initialize() {
        list.add("Power Wash");
        list.add("Electric Mechanic");
        list.add("Plumber");
        list.add("Other");


        chooseService = findViewById(R.id.chooseservice);
        serviceDescription = findViewById(R.id.servicedescription);
        datePicker = findViewById(R.id.datepicker);
        tv_timePicker = findViewById(R.id.timepicker);
        serviceRequest = findViewById(R.id.servicerequest);
        progressDialog = new ProgressDialog(ServiceAdd.this);
        progressDialog.setTitle("Adding Service");
        progressDialog.setMessage("loading . . . . ");
    }
}