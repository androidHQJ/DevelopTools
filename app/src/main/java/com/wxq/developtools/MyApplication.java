package com.wxq.developtools;

import com.orhanobut.logger.Logger;
import com.wxq.commonlibrary.base.BaseApp;
import com.wxq.commonlibrary.util.L;
import com.wxq.developtools.activity.MainActivity;
import com.zxy.recovery.callback.RecoveryCallback;
import com.zxy.recovery.core.Recovery;

/**
 * Created by Administrator on 2018/6/23 0023.
 */

public class MyApplication extends BaseApp {
    @Override
    public void onCreate() {
        super.onCreate();
    }

    private void initRecovery() {
        Recovery.getInstance()
                .debug(BuildConfig.DEBUG)
                .recoverStack(true)
                .mainPage(MainActivity.class)
                .init(this);
    }

    @Override
    public boolean setIsDebug() {
        return BuildConfig.DEBUG;
    }

    @Override
    public void dealWithException(Thread thread, Throwable throwable, String error) {}
}
