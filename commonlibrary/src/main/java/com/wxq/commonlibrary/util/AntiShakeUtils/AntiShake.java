package com.wxq.commonlibrary.util.AntiShakeUtils;

/**
 * Created by heqijun on 2017/11/8.
 * 静态变量存储点击事件，可以定制存储的数量超出了可以自动删除之前的
 */

public class AntiShake {
    private static LimitQueue<OneClick> queue = new LimitQueue<>(20);

    public static boolean check(Object o) {
        String flag;
        if(o == null) {
            flag = Thread.currentThread().getStackTrace()[2].getMethodName();
        } else {
            flag = o.toString();
        }
        for (OneClick util : queue.getArrayList()) {
            if (util.getMethodName().equals(flag)) {
                return util.check();
            }
        }
        OneClick clickUtil = new OneClick(flag);
        queue.offer(clickUtil);
        return clickUtil.check();
    }
}
