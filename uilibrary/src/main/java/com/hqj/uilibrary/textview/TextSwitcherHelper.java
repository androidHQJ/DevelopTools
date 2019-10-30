package com.hqj.uilibrary.textview;

import android.graphics.Color;

import java.util.List;

public class TextSwitcherHelper {
    public interface OnTextSwitcherListener {
        void onClickResult(Object o);
    }

    public static class Builder{
        private VerticalTextview textSwitcher;
        private OnTextSwitcherListener onTextSwitcherListener;

        public Builder() {}

        public Builder setUpWithSwitcher(VerticalTextview textSwitcher){
            this.textSwitcher = textSwitcher;
            return this;
        }

        public Builder setOnTextSwitcherListener(OnTextSwitcherListener onTextSwitcherListener){
            this.onTextSwitcherListener = onTextSwitcherListener;
            return this;
        }

        public void start(final List<String> titles){
            textSwitcher.setTextList(titles);//加入显示内容,集合类型
            textSwitcher.setText(20, 5, Color.RED);//设置属性,具体跟踪源码
            textSwitcher.setTextStillTime(3000);//设置停留时长间隔
            textSwitcher.setAnimTime(250);//设置进入和退出的时间间隔

            textSwitcher.setOnItemClickListener(new VerticalTextview.OnItemClickListener() {
                @Override
                public void onItemClick(int position) {
                    onTextSwitcherListener.onClickResult(titles.get(position));
                }
            });
        }

        public void startAutoScroll(){
            textSwitcher.startAutoScroll();
        }

        public void stopAutoScroll(){
            textSwitcher.stopAutoScroll();
        }
    }
}
