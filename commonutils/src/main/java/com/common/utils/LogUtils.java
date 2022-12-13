package com.common.utils;

import android.util.Log;

public class LogUtils {

    public static String TAG = "LogUtils";
    public static boolean isShowTAG = false;

    public static void i(String tag, String msg) {
        if (!isShowTAG ) return;
        Log.i(tag, getLocation() + " " + msg);
    }

    public static void w( String msg) {
        if (!isShowTAG ) return;
        Log.w(TAG, getLocation() + " " + msg);
    }

    public static void w(String tag, String msg) {
        if (!isShowTAG ) return;
        Log.w(tag, getLocation() + " " + msg);
    }

    public static void i(String msg) {
        if (!isShowTAG ) return;
        Log.i(TAG, getLocation() + " " + msg);
    }

    public static void e(String tag, String msg) {
        if (!isShowTAG ) return;
        Log.e(tag, getLocation() + " " + msg);
    }

    public static void e(String msg) {
        if (!isShowTAG ) return;
        Log.e(TAG, getLocation() + " " + msg);
    }

    /**
     * 输出日志的位置
     *
     * @return
     */
    private static String getLocation() {
        String result = "";
        try {
            StackTraceElement stackTraceElement = new Exception().getStackTrace()[2];
            String position = stackTraceElement.toString();
            int lineNumber = (stackTraceElement.getLineNumber() + "").length();
            result = position.substring(position.length() - (stackTraceElement.getFileName().length() + lineNumber + 2 + 1));
        } catch (Exception e) {

        }
        return result;
    }

}
