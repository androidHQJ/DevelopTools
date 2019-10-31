package com.wxq.developtools;

import com.wxq.commonlibrary.base.BaseApp;

/**
 * Created by Administrator on 2018/6/23 0023.
 */

public class MyApplication extends BaseApp {
    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public boolean setIsDebug() {
        return BuildConfig.DEBUG;
    }

    @Override
    public void dealWithException(Thread thread, Throwable throwable, String error) {}
}
