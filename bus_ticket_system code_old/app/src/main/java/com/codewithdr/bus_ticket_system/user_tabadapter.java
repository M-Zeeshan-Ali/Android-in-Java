package com.codewithdr.bus_ticket_system;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class user_tabadapter extends FragmentStatePagerAdapter {

    List<Fragment> fragmentList=new ArrayList<>();
    List<String>  nameList=new ArrayList<>();


    public void addfragment(Fragment fragment,String name)
    {
        fragmentList.add(fragment);
        nameList.add(name);

    }


    public user_tabadapter(@NonNull @NotNull FragmentManager fm) {
        super(fm);
    }


    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return nameList.get(position);
    }

    @NonNull
    @org.jetbrains.annotations.NotNull
    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }
}
