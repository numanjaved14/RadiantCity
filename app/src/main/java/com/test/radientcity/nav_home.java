package com.test.radientcity;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class nav_home extends AppCompatActivity {

    private fragment_home fragment_Home;
    private fragment_bill fragment_Bill;
    private fragment_service fragment_Service;
    private fragment_complain fragment_Complain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav_home);

        initialize();

        setFragment(fragment_Home);

        onNavigation();
    }

    private void initialize() {
        FrameLayout mMain_Frame = (FrameLayout) findViewById(R.id.main_frame);
        fragment_Home = new fragment_home();
        fragment_Bill = new fragment_bill();
        fragment_Service = new fragment_service();
        fragment_Complain = new fragment_complain();
    }

    private void onNavigation() {
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.nav_home);

        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){

                    case R.id.home:
                        setFragment(fragment_Home);
                        return true;
                    case R.id.bill:
                        setFragment(fragment_Bill);
                        return true;
                    case R.id.complain:
                        setFragment(fragment_Complain);
                        return true;
                    case R.id.service:
                        setFragment(fragment_Service);
                        return true;

                }
                return false;
            }
        });
    }

    private void setFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.main_frame, fragment);
        fragmentTransaction.commit();
    }
}
