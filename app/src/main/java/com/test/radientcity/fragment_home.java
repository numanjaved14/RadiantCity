package com.test.radientcity;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import com.test.radientcity.Adapters.RecyclerViewAdapter;
import com.test.radientcity.Adapters.SliderAdapter;
import com.test.radientcity.Adapters.SliderAdapter;
import com.test.radientcity.DataModels.Datamodel_announce;

import java.util.ArrayList;
import java.util.List;

public class fragment_home extends Fragment {

    List<Datamodel_announce> list = new ArrayList<Datamodel_announce>();

    RecyclerView recyclerView;

    ViewPager2 viewPager2;
    Handler slideHandler = new Handler();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        //Slider code here
        viewPager2 = view.findViewById(R.id.viewPagerImageSlider);

        // TODO: Load images from API
        List<SliderItem> sliderItems = new ArrayList<>();
        sliderItems.add(new SliderItem(R.drawable.ic_launcher_background));
        sliderItems.add(new SliderItem(R.drawable.ic_launcher_background));
        sliderItems.add(new SliderItem(R.drawable.ic_launcher_background));
        sliderItems.add(new SliderItem(R.drawable.ic_launcher_background));
        sliderItems.add(new SliderItem(R.drawable.ic_launcher_background));

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

        //Recyclerview code here
        list.add(new Datamodel_announce("name","desc",R.drawable.ic_launcher_background));
        list.add(new Datamodel_announce("name","desc",R.drawable.ic_launcher_background));
        list.add(new Datamodel_announce("name","desc",R.drawable.ic_launcher_background));
        list.add(new Datamodel_announce("name","desc",R.drawable.ic_launcher_background));
        list.add(new Datamodel_announce("name","desc",R.drawable.ic_launcher_background));

        recyclerView = view.findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(getActivity(), list);
        recyclerView.setAdapter(recyclerViewAdapter);

        return view;
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