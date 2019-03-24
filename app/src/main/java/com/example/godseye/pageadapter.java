package com.example.godseye;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class pageadapter extends FragmentStatePagerAdapter {
    int NoofTabs;
    public pageadapter(FragmentManager fm,int NoofTabs) {
        super(fm);
        this.NoofTabs=NoofTabs;
    }

    @Override
    public Fragment getItem(int i) {
        switch (i) {
            case 0:
                return new tabone();
            case 1:
                return new tabtwo();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return NoofTabs;
    }
}
