package com.example.theoden.rudimental;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ViewPageManager extends FragmentPagerAdapter {
    MetronomeUI metronomeUI;
    public ViewPageManager(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        switch (i) {
            case 0:
                if (metronomeUI == null) {
                    System.out.println("Metronome is null");
                    metronomeUI = MetronomeUI.newInstance();
                } else {
                    System.out.println("Metronome already instantiated");
                }
                return metronomeUI;
            default:
                Fragment fragment = new ObjectFragment();
                Bundle args = new Bundle();
                args.putInt(ObjectFragment.ARG_OBJECT, i + 1);
                fragment.setArguments(args);
                return fragment;
        }
    }

    public int getCount() {
        return 3;
    }
}

