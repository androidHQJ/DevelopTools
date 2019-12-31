package com.wxq.commonlibrary.base.service;

import android.content.Intent;
import android.os.FileObserver;
import android.util.Log;

import com.wxq.commonlibrary.constant.GlobalContent;
import com.wxq.commonlibrary.util.NetworkUtils;

import java.io.File;

/**
 * 实现本地文件夹的监测和上传文件到阿里的OSS服务器上
 * 由于这个功能不需要界面而且需要程序退到后台时依然监测，所以初步定为用服务实现。
 */
public class RecordUploadService extends BaseWorkerService {
    private String TAG = getClass().getCanonicalName();
    public static final String ACTION_UPLOAD = "ACTION_UPLOAD";
    private RecordFileListener recordFileListener = null;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public String getWorkerTag() {
        return RecordUploadService.class.getSimpleName();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (recordFileListener != null)
            recordFileListener.stopWatching();
    }

    @Override
    public void onWorkerRequest(Intent intent, int startId) {
        Log.d(TAG, "receive Request");
        if (ACTION_UPLOAD.equals(intent.getAction())) {
            String observeDir = GlobalContent.SAVEFILEPATH;
//            String remoteDir = intent.getStringExtra("remoteDir");
            if (recordFileListener != null) {
                recordFileListener.stopWatching();
                recordFileListener = null;
            }
            //TODO：监测文件夹文件创建事件可用FileObserver
            recordFileListener = new RecordFileListener(observeDir);
            recordFileListener.startWatching();
            Log.d(TAG, "start watching directory " + observeDir);
        }
    }

    private class RecordFileListener extends FileObserver {
        private String recordDirName;
//        private String remoteDir;

        private RecordFileListener(String path) {
            super(path);
            recordDirName = path;
//            this.remoteDir = remoteDir;
        }

        @Override
        public void onEvent(int event, String path) {
            switch (event) {
                case FileObserver.CREATE:
                    Log.d(TAG, "Record has been created: " + path);
                    // 如果wifi连通，上传至OSS服务器
                    if (NetworkUtils.isWifiConnected()) {
                        File recordDir = new File(recordDirName);
                        if (recordDir.exists() && recordDir.isDirectory()) {
                            for (File file : recordDir.listFiles()) {
                                upload(file);
                            }
                        }
                    }
                    break;
            }
        }

        //TODO:上传文件
        private void upload(File file) {

        }
    }
}
