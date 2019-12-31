package com.wxq.commonlibrary.base.service;

import android.content.Context;
import android.content.Intent;
import android.os.Message;
import android.os.PowerManager;
import android.util.Log;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 第一个需要确定能否用IntentService实现呢，因为IntentService会自动创建新的线程，
 * 只允许startService启动，而且消息队列也帮我们管理了，省了很多功夫，
 * 但是IntentService会自动停止服务，似乎不适合我们的业务需求，
 * 因此，我们需要自己创建线程和管理消息队列了，所以首先上服务基础类的代码。
 * 注意，我们只允许startService启动服务，因为该service不是随调用者停止而停止的。
 */
public abstract class BaseWorkerService extends BaseService {

    private final Lock mWorkerLock = new ReentrantLock();

    public abstract String getWorkerTag();

    public abstract void onWorkerRequest(Intent intent, int startId);

    @Override
    public void onCreate() {
        super.onCreate();

        startWorker(getWorkerTag());
    }

    @Override
    protected void handleStart(Intent intent, int startId) {
        try {
            Message msg = getWorkerHandler().obtainMessage();
            msg.what = startId;
            msg.obj = intent;
            getWorkerHandler().dispatchMessage(msg);
        } catch (Exception e) {
            Log.e("WorkerService", e.getMessage());
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        endWorker();
    }

    @Override
    protected void onWorkerRequest(Message msg) {
        mWorkerLock.lock();
        acquireWakeLock();
        try {
            onWorkerRequest((Intent) msg.obj, msg.what);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            releaseWakeLock();
            mWorkerLock.unlock();
        }
    }

    /**
     * 各种锁的类型对CPU 、屏幕、键盘的影响：
     PARTIAL_WAKE_LOCK:保持CPU 运转，屏幕和键盘灯有可能是关闭的。
     SCREEN_DIM_WAKE_LOCK：保持CPU 运转，允许保持屏幕显示但有可能是灰的，允许关闭键盘灯
     SCREEN_BRIGHT_WAKE_LOCK：保持CPU 运转，允许保持屏幕高亮显示，允许关闭键盘灯
     FULL_WAKE_LOCK：保持CPU 运转，保持屏幕高亮显示，键盘灯也保持亮度
     ACQUIRE_CAUSES_WAKEUP：强制使屏幕亮起，这种锁主要针对一些必须通知用户的操作.
     ON_AFTER_RELEASE：当锁被释放时，保持屏幕亮起一段时间

     最后别忘了声明权限：
     <uses-permission android:name="android.permission.WAKE_LOCK"/>
     <uses-permission android:name="android.permission.DEVICE_POWER"/>
     */
    PowerManager.WakeLock wakeLock = null;

    //获取电源锁，保持该服务在屏幕熄灭时仍然获取CPU时，保持运行
    private void acquireWakeLock() {
        if (null == wakeLock) {
            PowerManager pm = (PowerManager) this.getSystemService(Context.POWER_SERVICE);
            wakeLock = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK | PowerManager.ON_AFTER_RELEASE, "PostLocationService");
            if (null != wakeLock) {
                wakeLock.acquire();
//                wakeLock.acquire(10 * 60 * 1000L /*10 minutes*/);
            }
        }
    }

    //释放设备电源锁
    private void releaseWakeLock() {
        if (null != wakeLock) {
            wakeLock.release();
            wakeLock = null;
        }
    }
}
