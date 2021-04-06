package com.test.radientcity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.makeramen.roundedimageview.RoundedImageView;
import com.test.radientcity.Adapters.RecyclerViewAdapter;
import com.test.radientcity.Adapters.SliderAdapter;
import com.test.radientcity.Adapters.SliderAdapter;
import com.test.radientcity.DataModels.Datamodel_announce;
import com.test.radientcity.DataModels.Dummy;

import java.util.ArrayList;
import java.util.List;

public class fragment_home extends Fragment {

    TextView tv_userName, tv_userEmail, tv_userAddress;
    ImageButton ib_logOut;
    RoundedImageView iv_userImage;
    RecyclerView recyclerView;
    RecyclerViewAdapter recyclerViewAdapter;
    ViewPager2 viewPager2;
    Handler slideHandler = new Handler();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        initializeViews(view);
        getData();

        ib_logOut.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(getActivity().getApplication(), MainActivity.class);
                startActivity(intent);
            }
        });
        //Slider code here
        viewPager2 = view.findViewById(R.id.viewPagerImageSlider);

        // TODO: Load images from API
        List<SliderItem> sliderItems = new ArrayList<>();
        sliderItems.add(new SliderItem(R.drawable.ic_launcher_background
                ,"https://images.unsplash.com/photo-1502736842968-bcaab72d0700?ixlib=rb-1.2.1&ixid=MXwxMjA3fDB8MHxleHBsb3JlLWZlZWR8MXx8fGVufDB8fHw%3D&w=1000&q=80"));
        sliderItems.add(new SliderItem(R.drawable.ic_launcher_background
                ,"https://images.unsplash.com/photo-1502736842968-bcaab72d0700?ixlib=rb-1.2.1&ixid=MXwxMjA3fDB8MHxleHBsb3JlLWZlZWR8MXx8fGVufDB8fHw%3D&w=1000&q=80"));
        sliderItems.add(new SliderItem(R.drawable.ic_launcher_background
                ,"https://images.unsplash.com/photo-1502736842968-bcaab72d0700?ixlib=rb-1.2.1&ixid=MXwxMjA3fDB8MHxleHBsb3JlLWZlZWR8MXx8fGVufDB8fHw%3D&w=1000&q=80"));
        sliderItems.add(new SliderItem(R.drawable.ic_launcher_background
                ,"https://images.unsplash.com/photo-1502736842968-bcaab72d0700?ixlib=rb-1.2.1&ixid=MXwxMjA3fDB8MHxleHBsb3JlLWZlZWR8MXx8fGVufDB8fHw%3D&w=1000&q=80"));
        sliderItems.add(new SliderItem(R.drawable.ic_launcher_background
                ,"https://images.unsplash.com/photo-1502736842968-bcaab72d0700?ixlib=rb-1.2.1&ixid=MXwxMjA3fDB8MHxleHBsb3JlLWZlZWR8MXx8fGVufDB8fHw%3D&w=1000&q=80"));

        viewPager2.setAdapter(new SliderAdapter(sliderItems, viewPager2));

        viewPager2.setClipToPadding(false);
        viewPager2.setClipChildren(false);
        viewPager2.setOffscreenPageLimit(3);
        viewPager2.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);

        CompositePageTransformer compositePageTransformer = new CompositePageTransformer();
        compositePageTransformer.addTransformer(new MarginPageTransformer(40));
        compositePageTransformer.addTransformer(new ViewPager2.PageTransformer() {
            @Override
            public void transformPage(@NonNull View page, float position) {
                float r = 1 - Math.abs(position);
                //page.setScaleY(0.85f + r + 0.15f);
            }
        });

        viewPager2.setPageTransformer(compositePageTransformer);

        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                slideHandler.removeCallbacks(sliderRunnable);
                slideHandler.postDelayed(sliderRunnable, 3000);
            }
        });
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Users");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                Dummy dummy = dataSnapshot.getValue(Dummy.class);
                tv_userName.setText(dummy.getName());
                tv_userEmail.setText(dummy.getEmail());
                Log.d("mydata", "Name: " + dummy.getName()+ ", email: "+dummy.getEmail());
                /*Toast.makeText(getContext(), "Name: " + dummy.getName()+ ", email: "+dummy.getEmail()
                        , Toast.LENGTH_LONG).show();*/
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("TAG", "Failed to read value.", error.toException());
            }
        });

        return view;
    }


    private void initializeViews(View view) {
        tv_userName = view.findViewById(R.id.user_name);
        tv_userEmail = view.findViewById(R.id.user_email);
        tv_userAddress = view.findViewById(R.id.user_address);
        iv_userImage = view.findViewById(R.id.user_image);
        ib_logOut = view.findViewById(R.id.logout);

        recyclerView = view.findViewById(R.id.recyclerview_home);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerViewAdapter = new RecyclerViewAdapter(getActivity());
        recyclerView.setAdapter(recyclerViewAdapter);
    }

    private void getData() {
        List<Datamodel_announce> list = new ArrayList<Datamodel_announce>();

        list.add(new Datamodel_announce("name", "desc", R.drawable.ic_launcher_background));
        list.add(new Datamodel_announce("name", "desc", R.drawable.ic_launcher_background));
        list.add(new Datamodel_announce("name", "desc", R.drawable.ic_launcher_background));
        list.add(new Datamodel_announce("name", "desc", R.drawable.ic_launcher_background));
        list.add(new Datamodel_announce("name", "desc", R.drawable.ic_launcher_background));
        recyclerViewAdapter.setList(list);

    }

    private Runnable sliderRunnable = new Runnable() {
        @Override
        public void run() {
            viewPager2.setCurrentItem(viewPager2.getCurrentItem() + 1);
        }
    };

    @Override
    public void onPause() {
        super.onPause();
        slideHandler.removeCallbacks(sliderRunnable);
    }

    @Override
    public void onResume() {
        super.onResume();
        slideHandler.postDelayed(sliderRunnable, 3000);
    }
}