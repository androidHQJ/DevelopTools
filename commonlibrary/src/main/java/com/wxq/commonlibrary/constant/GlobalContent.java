package com.wxq.commonlibrary.constant;

import android.os.Environment;

/**
 * @author nat.xue
 * @date 2017/10/21
 * @description 全局常量
 */

public class GlobalContent {

    public static String filePath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/DevelopTools/";

    /**
     * 语音存放路径
     */
    public static String VOICEPATH=filePath+"audio/";

    /**
     * 视频存放路径
     */
    public static String VIDEOPATH=filePath+"video/";

    /**
     * 课件保存路径
     */
    public static String COURSEWARE = filePath + "courseware/";

    /**
     * 图片保存路径
     */
    public static String SAVEPICTURE = filePath + "savepictures/";

    /**
     * 选图片缓存和glide缓存的图片路径
     */
    public static String imgPath= filePath + "savepictures/";

    /**
     * 文件的存放地址
     */
    public static String SAVEFILEPATH= filePath + "savefile/";

    /**
     * log路径
     */
    public static String logPath=filePath + "log/";

    /**
     * baseurl
     */
    public static final String BASE_URL = "http://www.wanandroid.com/tools/mockapi/2547/";

}
