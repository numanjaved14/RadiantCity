package com.test.radientcity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    EditText et_username;
    EditText et_pass;
    Button login;
    private FirebaseAuth mAuth;
    private FirebaseUser currentUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FirebaseApp.initializeApp(getApplicationContext());
        intializeView();
        getValues();
        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        currentUser = mAuth.getCurrentUser();
        if(currentUser!=null){
            openactivity2();
        }
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
                } else if (pass.length() < 6) {
                    Toast.makeText(getApplicationContext(), "Password must be minimum of length 8", Toast.LENGTH_SHORT).show();
                } else {
                    signUp(user,pass);
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

    public void signUp(String email,String password){
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            currentUser = mAuth.getCurrentUser();
                            openactivity2();
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(getApplicationContext(), "Authentication failed.",
                                    Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }
}