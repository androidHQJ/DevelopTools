package com.hqj.uilibrary.viewpager;

import android.util.SparseIntArray;

import com.tmall.ultraviewpager.UltraViewPager;
import com.tmall.ultraviewpager.transformer.UltraScaleTransformer;

public class UltraViewPagerHelper {

    public static class Builder{
        private UltraViewPager ultraViewpager;
        private UltraPagerAdapter ultraPagerAdapter;

        public Builder setUpWithBanner(UltraViewPager ultraViewpager) {
            this.ultraViewpager = ultraViewpager;
            return this;
        }

        public Builder setInfiniteLoop(boolean enableLoop) {
            ultraViewpager.setInfiniteLoop(enableLoop);
            return this;
        }

        public Builder setAutoScroll(boolean enableAutoScroll) {
            if (enableAutoScroll) {
                SparseIntArray special = new SparseIntArray();
                special.put(0, 5000);
                special.put(1, 1500);
                ultraViewpager.setAutoScroll(1000, special);
            } else
                ultraViewpager.disableAutoScroll();
            return this;
        }

        public void build(){
            ultraViewpager.setScrollMode(UltraViewPager.ScrollMode.HORIZONTAL);
            ultraPagerAdapter = new UltraPagerAdapter();
            ultraViewpager.setAdapter(ultraPagerAdapter);
            ultraViewpager.setMultiScreen(0.6f);
            ultraViewpager.setItemRatio(1.0f);
            ultraViewpager.setRatio(2.0f);
            ultraViewpager.setMaxHeight(800);
            ultraViewpager.setAutoMeasureHeight(true);
            ultraViewpager.setPageTransformer(false, new UltraScaleTransformer());
        }

    }

}
