package com.spect.mytunas.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.spect.mytunas.fragment.LoginFragment;
import com.spect.mytunas.fragment.RegisterFragment;

public class PagerAdapter extends FragmentPagerAdapter {

    private int numOfTabs;

    public PagerAdapter(FragmentManager fm, int numOfTabs){
        super(fm);
        this.numOfTabs = numOfTabs;
    }
    @NonNull
    @Override
    public Fragment getItem(int position) {

        switch (position){
            case 0:
                LoginFragment loginFragment = new LoginFragment();
                return new LoginFragment();
            case 1:
                RegisterFragment registerFragment = new RegisterFragment();
                return new RegisterFragment();
                default:
                    return null;
        }

    }

    @Override
    public int getCount() {
        return numOfTabs;
    }
}
