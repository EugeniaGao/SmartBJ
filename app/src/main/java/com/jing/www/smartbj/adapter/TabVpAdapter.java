package com.jing.www.smartbj.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by Administrator on 2017/2/6.
 */
public class TabVpAdapter extends FragmentPagerAdapter {
 //我要绑定多个fragment,就要传入fragment的集合
    private List<Fragment> fragments;
    public TabVpAdapter(FragmentManager fm,List<Fragment> fragments) {
        super(fm);
        this.fragments=fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
}
