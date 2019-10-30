package com.hqj.uilibrary.banner;

import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;

public class BannerHelper {

    public interface OnCustomBannerListener {
        void onClickResult(Object o);
    }

    public static class Builder{

        private Banner banner;
        private List<?> imageUrls=new ArrayList<>();
        private List<String> titles;
        private OnCustomBannerListener listener;

        public Builder() {}

        public Builder setUpWithBanner(Banner banner) {
            this.banner = banner;
            return this;
        }

        public Builder buildImages(List<?> imageUrls) {
            this.imageUrls = imageUrls;
            return this;
        }

        public Builder buildBannerTitles(List<String> titles) {
            this.titles = titles;
            return this;
        }

        public Builder setOnBannerListener(OnCustomBannerListener listener) {
            this.listener = listener;
            return this;
        }

        public Builder start(){
            banner.setImages(imageUrls)
                    .setImageLoader(new GlideImageLoader())
                    .setBannerTitles(titles)
                    .setOnBannerListener(new OnBannerListener() {
                        @Override
                        public void OnBannerClick(int position) {
                            if (imageUrls!=null&&imageUrls.size()>0){
                                listener.onClickResult(imageUrls.get(position));
                            }
                        }
                    })
                    .start();
            return this;
        }

        public void update(List<?> imageUrls){
            banner.update(imageUrls);
        }

        public void startAutoPlay(){
            //开始轮播
            banner.startAutoPlay();
        }

        public void stopAutoPlay(){
            //结束轮播
            banner.stopAutoPlay();
        }

    }
}
