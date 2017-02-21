package com.example.gn.hlshop.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.example.gn.hlshop.R;
import com.example.gn.hlshop.adapter.MyVpAdapter;
import com.example.gn.hlshop.fragment.CartFragment;
import com.example.gn.hlshop.fragment.CategoryFragment;
import com.example.gn.hlshop.fragment.HomeFragment;
import com.example.gn.hlshop.fragment.HotFragment;
import com.example.gn.hlshop.fragment.MineFragment;

import java.util.ArrayList;

/**
 * Created by GN on 2017/2/12.
 */
public class ShowMain extends FragmentActivity{
    ViewPager vPager;
    FragmentManager fm;
    LinearLayout myviewpager1,myviewpager2,myviewpager3,myviewpager4,myviewpager5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.showmain);
        initData();
        initView();
        initViewPager();
    }
    private void initView(){
        vPager = (ViewPager) findViewById(R.id.vPager);
        myviewpager1 = (LinearLayout) findViewById(R.id.myviewpager1);
        myviewpager2 = (LinearLayout) findViewById(R.id.myviewpager2);
        myviewpager3 = (LinearLayout) findViewById(R.id.myviewpager3);
        myviewpager4 = (LinearLayout) findViewById(R.id.myviewpager4);
        myviewpager5 = (LinearLayout) findViewById(R.id.myviewpager5);
        fm = getSupportFragmentManager();
    }

    public ArrayList<Fragment> initData(){
        ArrayList<Fragment> list = new ArrayList<Fragment>();
        CartFragment main1 = new CartFragment();
        CategoryFragment main2 = new CategoryFragment();
        HomeFragment main3 = new HomeFragment();
        HotFragment main4 = new HotFragment();
        MineFragment main5 = new MineFragment();
        list.add(main3);
        list.add(main4);
        list.add(main2);
        list.add(main1);
        list.add(main5);
        Log.e("TAG", "init() 方法运行了么？");
        return list;
    }
    public void initViewPager(){
        //默认第一个选项颜色改变
        vPager.setAdapter(new MyVpAdapter(fm, initData()));
        //让ViewPager缓存5个页面
        vPager.setOffscreenPageLimit(3);
        //设置默认打开第一页
        vPager.setCurrentItem(0);
        Log.e("TAG", "第一页打开没有？");
        //进行页面编号的监听
        vPager.setOnPageChangeListener(new MyViewPageOnChange());
        myviewpager1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                vPager.setCurrentItem(0);
            }
        });
        myviewpager2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                vPager.setCurrentItem(1);
            }
        });
        myviewpager3.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                vPager.setCurrentItem(2);
            }
        });
        myviewpager4.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                vPager.setCurrentItem(3);
            }
        });
        myviewpager5.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                vPager.setCurrentItem(4);
            }
        });

    }

    //实现对viewager的监听
    //给 viewpage添加变化监听
    public class MyViewPageOnChange  implements ViewPager.OnPageChangeListener{

        @Override
        public void onPageScrollStateChanged(int arg0) {
            //开始滑动变化时
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {
            //滑动结束时
        }

        @Override
        public void onPageSelected(int arg0) {
           /* switch(arg0){
                case 0:
                    tv1.setTextColor(Color.parseColor("#ffffff"));
                    tv2.setTextColor(Color.parseColor("#FCCBC7"));
                    tv3.setTextColor(Color.parseColor("#FCCBC7"));
                    break;
                case 1:
                    tv2.setTextColor(Color.parseColor("#ffffff"));
                    tv1.setTextColor(Color.parseColor("#FCCBC7"));
                    tv3.setTextColor(Color.parseColor("#FCCBC7"));
                    break;
                case 2:
                    tv3.setTextColor(Color.parseColor("#ffffff"));
                    tv2.setTextColor(Color.parseColor("#FCCBC7"));
                    tv1.setTextColor(Color.parseColor("#FCCBC7"));
                    break;
            }*/
        }

    }
}
