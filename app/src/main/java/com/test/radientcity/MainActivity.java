package com.test.radientcity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText et_username;
    EditText et_pass;
    Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        intializeView();
        getValues();
    }

    private void intializeView() {
        et_username = findViewById(R.id.et_username);
        et_pass = findViewById(R.id.et_pass);
        login = findViewById(R.id.login);
    }

    private void getValues() {
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String user = et_username.getText().toString();
                String pass = et_pass.getText().toString();
                if (user.isEmpty() && pass.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Username and Password must be required", Toast.LENGTH_SHORT).show();
                } else if (pass.length() < 8) {
                    Toast.makeText(getApplicationContext(), "Password must be minimum of length 8", Toast.LENGTH_SHORT).show();
                } else {
                    openactivity2();


                    Log.w("username", "" + et_username.getText());
                    Log.w("password", "" + et_pass.getText());
                }
            }
        });
    }

    private void openactivity2() {
        Intent intent = new Intent(this, nav_home.class);
        startActivity(intent);
    }

}