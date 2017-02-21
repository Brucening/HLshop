package com.example.gn.hlshop.fragment;

import android.annotation.TargetApi;
import android.app.DownloadManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.Indicators.PagerIndicator;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
import com.example.gn.hlshop.R;
import com.example.gn.hlshop.adapter.HomeCategoryAdapter;
import com.example.gn.hlshop.adapter.decoration.DividerItemDecoration;
import com.example.gn.hlshop.entity.Banner;
import com.example.gn.hlshop.entity.Campaign;
import com.example.gn.hlshop.entity.HomeCampaign;
import com.example.gn.hlshop.http.BaseCallBack;
import com.example.gn.hlshop.http.Contants;
import com.example.gn.hlshop.http.OkHttpHelper;
import com.example.gn.hlshop.http.SpotsCallBack;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;

import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by GN on 2017/1/12.
 */
public class HomeFragment extends Fragment {
    private SliderLayout mSliderLayout;
    private PagerIndicator mIndicater;
    protected RecyclerView mRecyclerView;
    private HomeCategoryAdapter mAdapter;
    private Gson mGson = new Gson();
    private List<Banner> mBanners;
    OkHttpHelper okHttpHelper = OkHttpHelper.getInstance();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home,container,false);
        mSliderLayout = (SliderLayout)view.findViewById(R.id.slider);
        mIndicater = (PagerIndicator)view.findViewById(R.id.custom_indicator);
        requestImages();
        initRecyclerView(view);
        return view;
    }

   //OKhttop
   private void requestImages(){
        //geturl
        String url = Contants.API.BANNER+"?type=1";

       okHttpHelper.get(url, new SpotsCallBack<List<Banner>>(getActivity()) {
           @Override
           public void onError(Response response, int code, Exception e) {

               dissmissDialog();
           }

           @Override
           public void onSuccess(Response response, List<Banner> banners) {
               mBanners = banners;
               initSlider();
           }
       });
//       okHttpHelper.get(url, new BaseCallBack<List<Banner>>() {
//           @Override
//           public void onRequestBefore(DownloadManager.Request request) {
//
//           }
//
//           @Override
//           public void onFailure(okhttp3.Request request, IOException e) {
//
//           }
//
//           @Override
//           public void onSuccess(Response response, List<Banner> banners) {
//               Log.e("TAG", "");
//               mBanners = banners;
//               initSlider();
//
//           }
//
//           @Override
//           public void onError(Response response, int code, Exception e) {
//
//           }
//
//           @Override
//           public void onRequestBefore(Request request) {
//
//           }
//       });

    }
    private void initRecyclerView(View view){
        mRecyclerView = (RecyclerView) view.findViewById(R.id.home_rv);
        okHttpHelper.get(Contants.API.CAMPAIGN_HOME, new BaseCallBack<List<HomeCampaign>>() {
            @Override
            public void onRequestBefore(DownloadManager.Request request) {

            }

            @Override
            public void onFailure(Request request, IOException e) {
                Log.e("TAG", "ffffffffffffffffffff"+e);
            }

            @Override
            public void onSuccess(Response response, List<HomeCampaign> homeCampaigns) {
                initData(homeCampaigns);
            }




            @Override
            public void onError(Response response, int code, Exception e) {
                Log.e("TAG", "eeeeeeeeeeeeeeeeeeee" + e);
            }

            @Override
            public void onRequestBefore(Request request) {

            }

            @Override
            public void onResponse(Response response) {

            }
        });
//        List<HomeCategory> datas = new ArrayList<>();
//        HomeCategory category = new HomeCategory("热门活动",R.drawable.img_big_1,
//                R.drawable.img_1_small1,R.drawable.img_1_small2);
//        datas.add(category);
//
//         category = new HomeCategory("品牌街",R.drawable.img_big_2,
//                R.drawable.img_2_small1,R.drawable.img_2_small2);
//        datas.add(category);
//
//        category = new HomeCategory("金融街 包赚翻",R.drawable.img_big_3,
//                R.drawable.img_3_small1,R.drawable.img_3_small2);
//        datas.add(category);
//
//        category = new HomeCategory("超值购",R.drawable.img_big_0,
//                R.drawable.img_0_small1,R.drawable.img_0_small2);
//        datas.add(category);
//        //添加适配器
//        mAdapter = new HomeCategoryAdapter(datas);
//        mRecyclerView.setAdapter(mAdapter);
//        mRecyclerView.setLayoutManager(new LinearLayoutManager(
//                this.getActivity()));
//        mRecyclerView.addItemDecoration(new DividerItemDecoration());
    }

    private void initData(List<HomeCampaign> homeCampaigns) {
        mAdapter = new HomeCategoryAdapter(homeCampaigns, getActivity());
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(
                this.getActivity()));
        mRecyclerView.addItemDecoration(new DividerItemDecoration());
        mAdapter.setOnCampaignClickListener(new HomeCategoryAdapter.OnCampaignClickListener() {
            @Override
            public void onClick(View view, Campaign campaign) {
                Toast.makeText(getActivity(),"")

            }
        });
    }
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private void initSlider() {
        if (mBanners != null) {
            for (Banner banner : mBanners) {
                TextSliderView textSliderView = new TextSliderView(getActivity());
                textSliderView
                        .description(banner.getDescription())
                        .image(banner.getImgUrl())
                        .setScaleType(BaseSliderView.ScaleType.Fit);//设置图片的宽和高
                mSliderLayout.addSlider(textSliderView);
                //设置指示器位置（默认）
                mSliderLayout.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
                //设置转场效果
                mSliderLayout.setPresetTransformer(SliderLayout.Transformer.RotateUp);
                //设置自定义的适配器
                mSliderLayout.setCustomIndicator(mIndicater);
                //设置自定义动画
                mSliderLayout.setCustomAnimation(new DescriptionAnimation());
                //设置时间
                mSliderLayout.setDuration(3000);
                //给SliderLayout设置监听事件
                mSliderLayout.addOnPageChangeListener(new ViewPagerEx.OnPageChangeListener() {
                    @Override
                    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                        //    Log.e("TAG", "onPageScrolled----------->1");//滚动中调用
                    }

                    @Override
                    public void onPageSelected(int position) {
                        // Log.e("TAG", "onPageSelected----------->2");//新页卡被选中时调用
                    }

                    @Override
                    public void onPageScrollStateChanged(int state) {
                        // Log.e("TAG", "onPageScrollStateChanged----------->3");//滚动状态改变时时候调用
                    }
                });
            }
        }
    }



   //再次进来的时候继续滚动
   @Override
    public void onStart() {
        mSliderLayout.startAutoCycle();
        super.onStart();
    }

   //绑定生命周期 结束同步
   @Override
    public void onStop() {
        mSliderLayout.stopAutoCycle();
        super.onStop();
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        if(hidden){
            mSliderLayout.stopAutoCycle();
        }else {
            mSliderLayout.startAutoCycle();
        }
        super.onHiddenChanged(hidden);
    }
}
