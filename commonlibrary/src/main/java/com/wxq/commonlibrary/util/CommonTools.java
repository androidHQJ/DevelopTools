package com.wxq.commonlibrary.util;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Notification;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.v4.content.FileProvider;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.orhanobut.logger.Logger;
import com.wxq.commonlibrary.R;
import com.wxq.commonlibrary.constant.GlobalContent;

import java.io.File;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.text.DecimalFormat;
import java.util.Random;

/**
 * @author ztn
 * @version V_5.0.0
 * @date 2016年2月18日
 * @description 应用程序的公共工具类
 */
public class CommonTools {



    public static String outputError(Throwable ex) {
        Writer writer = new StringWriter();
        PrintWriter printWriter = new PrintWriter(writer);
        ex.printStackTrace(printWriter);
        Throwable cause = ex.getCause();

        if (cause != null) {
            cause.printStackTrace(printWriter);
        }
        String message = writer.toString();
        Logger.e(message);
        return message;
    }


    private static DecimalFormat df6 = new DecimalFormat("000000");
    private static Random rand = new Random();

    /**
     * 获取6位随机码
     */
    public static String getSixRandomNo() {
        int[] array = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        for (int i = array.length; i > 1; i--) {
            int index = rand.nextInt(i);
            int tmp = array[index];
            array[index] = array[i - 1];
            array[i - 1] = tmp;
        }
        int result = 0;
        for (int i = 0; i < 6; i++) {
            result = result * 10 + array[i];
        }
        return df6.format(result);
    }

    public static void setAlarmParams(Notification notification, String audio) {
        // AudioManager provides access to volume and ringer mode control.
        if (!TextUtils.isEmpty(audio)) {
            int a = Integer.parseInt(audio);
            // 获取系统设置的铃声模式
            switch (a) {
                case 0:
                    // 静音模式，值为0，这时候不震动，不响铃
                    notification.sound = null;
                    notification.vibrate = null;
                    notification.defaults = Notification.DEFAULT_LIGHTS;
                    break;
                case 1:
                    // 震动模式，值为1，这时候震动，不响铃
                    notification.sound = null;
                    notification.defaults = Notification.DEFAULT_VIBRATE;
                    break;
                case 2:
                    // 获取软件的设置
                    notification.defaults = Notification.DEFAULT_SOUND;
                    notification.vibrate = null;
                    break;
                case 3:
                    // 声音加振动
                    notification.defaults = Notification.DEFAULT_VIBRATE | Notification.DEFAULT_SOUND;
                    break;
                default:
                    break;
            }
        }
    }

    /**
     * 显示软键盘
     */
    public static void showInput(EditText et_msg) {
        try {
            et_msg.setFocusable(true);
            et_msg.setFocusableInTouchMode(true);
            et_msg.requestFocus();
            InputMethodManager inputManager = (InputMethodManager) et_msg.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            inputManager.showSoftInput(et_msg, 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 隐藏软键盘
     */
    public static void hideInput(EditText et_msg) {
        try {
            InputMethodManager imm = (InputMethodManager) et_msg.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(et_msg.getWindowToken(), 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 隐藏软键盘
     */
    public static void hideInput(Activity activity) {
        View v = activity.getCurrentFocus();
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
        }
    }


    public static boolean containInt(int[] array, int value) {
        for (int i = 0; i < array.length; i++) {
            if (value == array[i]) {
                return true;
            }
        }
        return false;
    }

    public static boolean containString(String[] array, String value) {
        int length = array.length;
        for (int i = 0; i < length; i++) {
            if (array[i].equals(value)) {
                return true;
            }
        }
        return false;
    }

    public static void copy(String content, Context context) {
        try {
            ClipboardManager cmb = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
            ClipData textCd = ClipData.newPlainText(content, content);
            cmb.setPrimaryClip(textCd);
            ToastUtils.showShort("已复制到剪切板");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 用浏览器打开
     */
    public static void openUrlWithBrower(Context context, String url) {
        if (StringUtils.isEmpty(url)) {
            return;
        }
        try {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            context.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
            ToastUtils.showShort("无法找到浏览器来打开链接");
        }
    }

    /**
     * 跳转到应用的详情界面
     *
     * @param activity
     */
    public static void gotoApplicationInfo(Activity activity) {
        try {
            Intent localIntent = new Intent();
            localIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            localIntent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
            localIntent.setData(Uri.fromParts("package", activity.getPackageName(), null));
            activity.startActivity(localIntent);
        } catch (Exception e) {
            e.printStackTrace();
            ToastUtils.showShort("无法跳转到应用详情界面，请手动前往打开权限");
        }
    }

    /**
     * 是否是主进程
     */
    public static boolean isMainProcess(Context context) {
        return context.getPackageName().equals(getCurrentProcessName(context));
    }

    private static String getCurrentProcessName(Context context) {
        int pid = android.os.Process.myPid();
        String processName = "";
        ActivityManager manager = (ActivityManager) context.getApplicationContext().getSystemService(Context.ACTIVITY_SERVICE);
        if (manager != null) {
            for (ActivityManager.RunningAppProcessInfo process : manager.getRunningAppProcesses()) {
                if (process.pid == pid) {
                    processName = process.processName;
                }
            }
        }
        return processName;
    }

    /**
     * 打开文件选择器
     */
    public static final int CHOOSE_FILE_REQUEST = 123;

    public static void openChooser(Activity context) {
        Intent i = new Intent(Intent.ACTION_GET_CONTENT);
        i.addCategory(Intent.CATEGORY_OPENABLE);
        i.setType("*/*");
        context.startActivityForResult(i, CHOOSE_FILE_REQUEST);
    }

    /**
     * 打开当前App的详情界面
     */
    public static void openAppInfo(Context context) {
        Intent intent = new Intent();
        intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Uri uri = Uri.fromParts("package", context.getPackageName(), null);
        intent.setData(uri);
        context.startActivity(intent);
    }

    public static final int TAKE_PHOTOS_REQUEST = 124;

    /**
     * 用系统相机拍照
     */
    public static Uri openSystemCamera(Activity context) {
        Intent intent = new Intent();
        intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
        File image = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM), System.currentTimeMillis() + ".jpg");
        FileUtils.createFileByDeleteOldFile(image);
        Uri fileUri = getFileUri(context, image);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);
        context.startActivityForResult(intent, TAKE_PHOTOS_REQUEST);
        return fileUri;
    }

    public static Uri getFileUri(Context context, File file) {
        Uri uriForFile;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            uriForFile = FileProvider.getUriForFile(context, context.getPackageName() + ".fileProvider", file);
        } else {
            uriForFile = Uri.fromFile(file);
        }
        return uriForFile;
    }

    /**
     * 打开系统相册
     */
    public static void openSystemAlbum(Activity context) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_PICK);
        intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
        context.startActivityForResult(intent, CHOOSE_FILE_REQUEST);
    }

    public static void openSystemSetting(Activity context) {
        Intent i = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS");
        String pkg = "com.android.settings";
        String cls = "com.android.settings.applications.InstalledAppDetails";
        i.setComponent(new ComponentName(pkg, cls));
        Uri uri = Uri.fromParts("package", context.getPackageName(), null);
        i.setData(uri);
        context.startActivity(i);
    }

}
