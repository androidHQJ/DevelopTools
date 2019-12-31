package com.wxq.developtools.activity;


import android.graphics.Color;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import com.hqj.uilibrary.banner.BannerHelper;
import com.hqj.uilibrary.textview.CountdownView;
import com.hqj.uilibrary.textview.TextSwitcherHelper;
import com.hqj.uilibrary.textview.VerticalTextview;
import com.hqj.uilibrary.viewpager.UltraViewPagerHelper;
import com.tmall.ultraviewpager.UltraViewPager;
import com.wxq.commonlibrary.base.BaseActivity;
import com.wxq.commonlibrary.base.BasePresenter;
import com.wxq.commonlibrary.util.ToastUtils;
import com.wxq.developtools.R;
import com.youth.banner.Banner;

import java.util.ArrayList;
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
    @BindView(R.id.countDownView)
    CountdownView countdownView;
    @BindView(R.id.auto_complete_text_view)
    AutoCompleteTextView autoCompleteTextView;
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

        //点击view时停止倒计时
        countdownView.setOnClickListener(v -> {
            //调用view停止倒计时方法
            countdownView.timeEnd();
            //去吧皮卡丘
            ToastUtils.showShort("点击结束倒计时");
        });

        //倒计时view相关设置
        //倒计时结束的回调监听
        countdownView.setMaxTime(5)                     //倒计时时间，单位秒
                .setConcatStr("s跳过广告页")              //倒计时后面拼接的字符串
                .setBgStyle(CountdownView.BgStyle.FILL) //背景样式 LINE：线  FILL:填充  CLEAR:不需要通过代码画背景
                .setBgColor(Color.RED)                  //要画背景的颜色值
                .setBgCorner(20)                        //要画背景的圆角值 单位dp
                .setEndListener(() -> {
                    //倒计时结束，去吧皮卡丘
                    ToastUtils.showShort("倒计时结束");
                })
                .timeStart(); //开始倒计时

        //设置数据
        List<String> datas = new ArrayList<>();
        datas.add("语文");
        datas.add("数学");
        datas.add("外语");
        datas.add("数学12");
        datas.add("数学234");
        datas.add("数学456");
        //创建适配器
        /**
         *
         一：在布局中需要加上android:completionThreshold="1"这句代码，设置输入一个字就提示，如果不写这句代码是不会提示你的。

         二：在代码中创建适配器的时候传入的第二个参数，是一个TextView而不是一个布局，
         否则会报这个错误java.lang.IllegalStateException: ArrayAdapter requires the resource ID to be a TextView，
         这里也可以直接传这个android.R.layout.simple_dropdown_item_1line默认的。
         */
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, datas);
        //绑定适配器
        autoCompleteTextView.setAdapter(adapter);

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
