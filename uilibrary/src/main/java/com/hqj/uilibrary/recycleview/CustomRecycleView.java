package com.hqj.uilibrary.recycleview;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by heqijun on 2018/6/28.
 * viewpager 嵌套横向滑动的RecycleView的滑动冲突
 */

public class CustomRecycleView extends RecyclerView {
    public CustomRecycleView(Context context) {
        super(context);
    }

    public CustomRecycleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomRecycleView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev){
        //其实这个方法为了通知他的父ViewPager现在进行的是本控件的操作，
        // 不要对我的操作进行干扰，如果是false的话当然就是父控件的操作可以覆盖当前操作。
        getParent().requestDisallowInterceptTouchEvent(true);
        return super.onInterceptTouchEvent(ev);
    }
}
