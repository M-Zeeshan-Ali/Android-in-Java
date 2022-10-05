package com.example.viewpagerexample;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

public class MainActivity extends AppCompatActivity {

    private android.app.Fragment FragmentViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewPager pager = (ViewPager) findViewById(R.id.pager);
        pager.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));
    }

    private class MyPagerAdapter extends FragmentPagerAdapter {

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int pos) {
            switch (pos) {
                case 0:
                    return Fragment.instantiate(getString(R.string.,R.drawable.A1);
                case 1:
                    return FragmentViewPager.instantiate(getString(R.string.title_section2), R.drawable.A1);
                case 2:
                    return FragmentViewPager.instantiate(getString(R.string.title_section3), R.drawable.B1);
                default:
                    return FragmentViewPager.instantiate(getString(R.string.title_section1), R.drawable.G1);
            }
        }

        @Override
        public int getCount() {
            return 3;
        }
    }