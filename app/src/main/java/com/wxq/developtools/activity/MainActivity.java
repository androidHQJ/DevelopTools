package com.wxq.developtools.activity;


import android.os.Bundle;

import com.hqj.uilibrary.banner.BannerHelper;
import com.hqj.uilibrary.textview.TextSwitcherHelper;
import com.hqj.uilibrary.textview.VerticalTextview;
import com.hqj.uilibrary.viewpager.UltraViewPagerHelper;
import com.tmall.ultraviewpager.UltraViewPager;
import com.wxq.commonlibrary.base.BaseActivity;
import com.wxq.commonlibrary.base.BasePresenter;
import com.wxq.developtools.R;
import com.youth.banner.Banner;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MainActivity extends BaseActivity {

    @BindView(R.id.banner)
    Banner banner;
    @BindView(R.id.tvSwitcher)
    VerticalTextview tvSwitcher;
    @BindView(R.id.ultra_viewpager)
    UltraViewPager ultraViewpager;
    private BannerHelper.Builder bannerHelper;
    private TextSwitcherHelper.Builder textSwitcherHelper;

    @Override
    protected void initViews() {
        bannerHelper = new BannerHelper.Builder()
                .setUpWithBanner(banner)
                .setOnBannerListener(o -> showToast(o + ""))
                .start();

        textSwitcherHelper = new TextSwitcherHelper.Builder()
                .setUpWithSwitcher(tvSwitcher)
                .setOnTextSwitcherListener(o -> showToast(o + ""));

        new UltraViewPagerHelper.Builder()
                .setUpWithBanner(ultraViewpager)
                .setInfiniteLoop(true)
                .setAutoScroll(false)
                .build();
    }

    @Override
    protected void initEventAndData() {
        String[] urls = getResources().getStringArray(com.hqj.uilibrary.R.array.url);
        List list = Arrays.asList(urls);
        bannerHelper.update(list);

        String[] titles = getResources().getStringArray(com.hqj.uilibrary.R.array.title);
        List<String> titleList = Arrays.asList(titles);
        textSwitcherHelper.start(titleList);
    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_main;
    }

    @Override
    protected BasePresenter initPresent() {
        return null;
    }

    @Override
    protected void onStart() {
        super.onStart();
        bannerHelper.startAutoPlay();
    }

    @Override
    protected void onPause() {
        super.onPause();
        textSwitcherHelper.stopAutoScroll();
    }

    @Override
    protected void onStop() {
        super.onStop();
        bannerHelper.stopAutoPlay();
    }

    @Override
    protected void onResume() {
        super.onResume();
        textSwitcherHelper.startAutoScroll();
    }
}
