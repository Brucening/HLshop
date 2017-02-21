package com.example.gn.hlshop.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TextView;

import com.example.gn.hlshop.R;
import com.example.gn.hlshop.entity.Tab;
import com.example.gn.hlshop.fragment.CartFragment;
import com.example.gn.hlshop.fragment.CategoryFragment;
import com.example.gn.hlshop.fragment.HomeFragment;
import com.example.gn.hlshop.fragment.HotFragment;
import com.example.gn.hlshop.fragment.MineFragment;
import com.example.gn.hlshop.view.FragmentTabHost;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends FragmentActivity {
    private FragmentTabHost mTabhost;
    private LayoutInflater mInflater;
    private List<Tab> mTabs = new ArrayList<Tab>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initTab();
    }

    private void initTab() {
        Tab tab_home = new Tab(HomeFragment.class,R.drawable.selector_icon_home,R.string.home);
        Tab tab_hot = new Tab(HotFragment.class,R.drawable.selector_icon_hot,R.string.hot);
        Tab tab_category = new Tab(CategoryFragment.class,R.drawable.selector_icon_category,R.string.category);
        Tab tab_cart = new Tab(CartFragment.class,R.drawable.selector_icon_cart,R.string.cart);
        Tab tab_mine = new Tab(MineFragment.class,R.drawable.selector_icon_mine,R.string.mine);
        mTabs.add(tab_home);
        mTabs.add(tab_hot);
        mTabs.add(tab_cart);
        mTabs.add(tab_category);
        mTabs.add(tab_mine);
        mTabhost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        mInflater = LayoutInflater.from(this);

        mTabhost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);
        for(Tab tab : mTabs){
            TabHost.TabSpec tabSpec = mTabhost.newTabSpec(getString(tab.getTitle()));
            tabSpec.setIndicator(buildIndIndicator(tab));
            mTabhost.addTab(tabSpec,tab.getFragment(),null);
        }
        mTabhost.getTabWidget().setShowDividers(LinearLayout.SHOW_DIVIDER_NONE);
        mTabhost.setCurrentTab(0);
    }
    private View buildIndIndicator(Tab tab){
        View view = mInflater.inflate(R.layout.tab_indicator,null);
        ImageView img = (ImageView) view.findViewById(R.id.tab_inicator_icon_iv);
        TextView text = (TextView) view.findViewById(R.id.tab_inicator_title_tv);
        img.setBackgroundResource(tab.getIcon());
        text.setText(tab.getTitle());
        return view;
    }
}
