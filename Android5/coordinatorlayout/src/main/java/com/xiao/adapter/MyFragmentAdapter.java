package com.xiao.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.xiao.coordinatorlayout.fragment.Fragment1;

/**
 * Created by ${qsh} on 2017/3/23.
 */

public class MyFragmentAdapter extends FragmentPagerAdapter {


    public MyFragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return Fragment1.newInstance();
    }

    @Override
    public int getCount() {
        return 2;
    }
}
