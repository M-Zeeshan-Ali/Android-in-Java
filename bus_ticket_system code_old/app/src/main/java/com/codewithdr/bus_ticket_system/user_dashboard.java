package com.codewithdr.bus_ticket_system;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

public class user_dashboard extends AppCompatActivity {


    com.google.android.material.tabs.TabLayout tabLayout;
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_dashboard);

        tabLayout=findViewById(R.id.tablayout);
        viewPager=findViewById(R.id.view_pager);




        user_tabadapter user_tabadapter=new user_tabadapter(getSupportFragmentManager());

        user_tabadapter.addfragment(new user_home(),"Home");
        user_tabadapter.addfragment(new user_booking(),"My Booking");
        user_tabadapter.addfragment(new user_profile(),"Profile");

        viewPager.setAdapter(user_tabadapter);
        tabLayout.setupWithViewPager(viewPager);
    }
}