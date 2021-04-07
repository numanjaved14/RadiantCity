package com.test.radientcity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
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
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.makeramen.roundedimageview.RoundedImageView;
import com.test.radientcity.Adapters.RecyclerViewAdapter;
import com.test.radientcity.Adapters.SliderAdapter;
import com.test.radientcity.Adapters.SliderAdapter;
import com.test.radientcity.DataModels.AnnouncementdataModel;
import com.test.radientcity.DataModels.Datamodel_announce;
import com.test.radientcity.DataModels.Dummy;
import com.test.radientcity.DataModels.User;

import java.util.ArrayList;
import java.util.List;

public class fragment_home extends Fragment {

    TextView tv_userName;
    ImageButton ib_logOut;
    RoundedImageView iv_userImage;
    RecyclerView recyclerView;
    RecyclerViewAdapter recyclerViewAdapter;
    ViewPager2 viewPager2;
    Handler slideHandler = new Handler();
    private FirebaseAuth mAuth;

    private List<AnnouncementdataModel> list = new ArrayList<AnnouncementdataModel>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        initializeViews(view);

        fetchAllAnnouncements();
        ib_logOut.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();

                Intent intent = new Intent(getActivity().getApplication(), MainActivity.class);
                startActivity(intent);
            }
        });
        //Slider code here
        viewPager2 = view.findViewById(R.id.viewPagerImageSlider);

        // TODO: Load images from API
        List<SliderItem> sliderItems = new ArrayList<>();
        sliderItems.add(new SliderItem(R.drawable.ic_launcher_background
                ,"https://zameenblog.s3.amazonaws.com/blog/wp-content/uploads/2019/05/cover-photo.jpg"));
        sliderItems.add(new SliderItem(R.drawable.ic_launcher_background
                ,"https://arcdn.zameen.com/area-guides/wp-content/uploads/2019/02/Bahria-Town_karachi-150120200551.jpg"));
        sliderItems.add(new SliderItem(R.drawable.ic_launcher_background
                ,"https://arcdn.zameen.com/area-guides/wp-content/uploads/2019/10/precinct32bahriatown-karachi-23102019226.jpg"));
        sliderItems.add(new SliderItem(R.drawable.ic_launcher_background
                ,"https://arcdn.zameen.com/area-guides/wp-content/uploads/2019/10/Precinct-15-Bahria-Town-Karachi-17102019951.jpg"));
        sliderItems.add(new SliderItem(R.drawable.ic_launcher_background
                ,"https://manahilestate.com/wp-content/uploads/2017/06/Bahria-Town-Karachi-Image.jpg"));

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
        mAuth = FirebaseAuth.getInstance();
          FirebaseUser firebaseUser= mAuth.getCurrentUser();
        loadUserData(firebaseUser.getUid());
         Log.d("mydata", "Name: " +firebaseUser.getDisplayName());

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Users");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                Dummy dummy = dataSnapshot.getValue(Dummy.class);

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

        ib_logOut = view.findViewById(R.id.logout);

        recyclerView = view.findViewById(R.id.recyclerview_home);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerViewAdapter = new RecyclerViewAdapter(getActivity());
        recyclerView.setAdapter(recyclerViewAdapter);
    }
    private void fetchAllAnnouncements() {
        list.clear();

        DatabaseReference firebaseRef = FirebaseDatabase.getInstance().getReference("Announcements");
        firebaseRef.addValueEventListener(new ValueEventListener() {
                                              @Override
                                              public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                                  if (dataSnapshot.getValue() != null) {
                                                      for (DataSnapshot shots : dataSnapshot.getChildren()) {
                                                          AnnouncementdataModel announcementdataModel = shots.getValue(AnnouncementdataModel.class);
                                                          assert announcementdataModel != null;


                                                              String title = announcementdataModel.getTitle();
                                                              String desc = announcementdataModel.getDescription();
                                                              String date = announcementdataModel.getDate();
                                                              String status = announcementdataModel.getStatus();
                                                              AnnouncementdataModel model = new AnnouncementdataModel(date, desc,status,title);
                                                               list.add(model);

                                                      }
                                                   if (list!=null && list.size()!=0){
                                                       recyclerViewAdapter.setList(list);
                                                   }

                                                  } else {
                                                      Toast.makeText(getActivity(), "No Announcement Found",
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
    private void loadUserData(final String id) {

        DatabaseReference firebaseRef = FirebaseDatabase.getInstance().getReference("Users").child(id);
        firebaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                User modelUser = dataSnapshot.getValue(User.class);
                String userId = modelUser.getFirebaseId();
                String name = modelUser.getUserName();
                tv_userName.setText(name);
                Log.d("dataserrt",name);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}