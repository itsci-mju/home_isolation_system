package com.example.miniproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

import com.example.miniproject.Class.MyAdapter_ApproveMedicine;
import com.example.miniproject.Class.MyFragmentAdapter;
import com.google.android.material.tabs.TabLayout;

public class TapLayout extends AppCompatActivity {
    private TabLayout tabLayout ;
    private ViewPager2 viewPager2 ;
    private MyFragmentAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tap_layout);

         tabLayout = findViewById(R.id.tapLayout) ;
         viewPager2 = findViewById(R.id.viewPage2) ;

        tabLayout.addTab(tabLayout.newTab().setText("รายการรออนุมัติ"));
        tabLayout.addTab(tabLayout.newTab().setText("ประวัติการอนุมัติ"));

        FragmentManager fragmentManager = getSupportFragmentManager();
        adapter = new MyFragmentAdapter(fragmentManager , getLifecycle());
        viewPager2.setAdapter(adapter);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager2.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                tabLayout.selectTab(tabLayout.getTabAt(position));
            }
        });


    }


    }
