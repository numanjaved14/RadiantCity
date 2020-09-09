package com.test.radientcity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class ServiceAdd extends AppCompatActivity {

    Spinner chooseService;
    TextView serviceDescription, datePicker, tv_timePicker;
    Button serviceRequest;
    Date date;
    SimpleDateFormat f12hours;
    DatePickerDialog dialog;

    String options[] = {"Power Wash", "Electric Mechanic", "Plumber", "Other"};
    String ServiceDate, ServiceTime;
    int hour, minute;

    private DatePickerDialog.OnDateSetListener dateSetListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_add);

        initialize();
        serviceSpinner();
        datePickerDialog();
        timePickerDialog();

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
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, options);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        chooseService.setAdapter(adapter);

        chooseService.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Object item = adapterView.getItemAtPosition(i);

                //TODO: Send the value of Service to API
                if (item != null) {

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void initialize() {
        chooseService = findViewById(R.id.chooseservice);
        serviceDescription = findViewById(R.id.servicedescription);
        datePicker = findViewById(R.id.datepicker);
        tv_timePicker = findViewById(R.id.timepicker);
        serviceRequest = findViewById(R.id.servicerequest);
    }
}