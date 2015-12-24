package com.towenqi.qianlv.memory;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.towenqi.qianlv.memory.fragment.MoonShapeFragment;
import com.towenqi.qianlv.memory.fragment.WelcomeCardFragment;
import com.towenqi.qianlv.memory.fragment.WelcomeMapFragment;
import com.towenqi.qianlv.memory.fragment.WelcomePageFace;

/**
 * Created by qianlv on 2015/9/5.
 */
public class PageAdapter extends FragmentStatePagerAdapter {
    private static final int NUM_PAGES = 4;

    public PageAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new WelcomeMapFragment();
            case 1:
                return new WelcomePageFace();
            case 2:
                return new WelcomeCardFragment();
            case 3:
                return new MoonShapeFragment();
        }
        return null;

    }

    @Override
    public int getCount() {
        return NUM_PAGES;
    }
}
