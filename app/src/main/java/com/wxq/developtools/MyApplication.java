package com.wxq.developtools;
import android.content.Intent;
import android.os.Process;

import com.umeng.analytics.MobclickAgent;
import com.wxq.commonlibrary.util.ActivityUtils;
import com.wxq.commonlibrary.base.BaseApp;
import com.wxq.developtools.activity.MainActivity;

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
    public void dealWithException(Thread thread, Throwable throwable, String error) {
        final Intent intent2 = new Intent();
        intent2.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent2.setClass(this, MainActivity.class);
        startActivity(intent2);
        MobclickAgent.onKillProcess(getApplicationContext());
        ActivityUtils.finishAllActivitiesExceptNewest();
        Process.killProcess(Process.myPid());
    }
}
