package com.example.gn.hlshop.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.View;

import java.util.ArrayList;

/**
 * Created by GN on 2017/2/12.
 */
public class MyVpAdapter extends FragmentPagerAdapter{
    //存放碎片的集合
    private ArrayList<Fragment> list;
    public MyVpAdapter(FragmentManager fm,ArrayList<Fragment> list) {
        super(fm);
        this.list = list;
    }

    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public void destroyItem(View container, int position, Object object) {
        super.destroyItem(container, position, object);
    }
}
