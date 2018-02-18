package com.stagap.stagap;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class TabsPagerAdapter extends FragmentPagerAdapter {

    int mNoOfTabs;

    public TabsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    public TabsPagerAdapter(FragmentManager fm, int numberOfTabs) {
        super(fm);
        mNoOfTabs = numberOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new LoginFragment();
            case 1:
                return new RegisterFragment();
        }
        return null;
    }

    @Override
    public int getCount() {
        return mNoOfTabs;
    }
}
