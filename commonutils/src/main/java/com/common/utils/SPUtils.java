package com.common.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public final class SPUtils {

    private final static String name = "config";
    private final static int mode = Context.MODE_PRIVATE;
    private static Context context;

    public static void init(Context c){
        context = c;
    }

    /**
     * 保存首选项
     *
     * @param key
     * @param value
     */
    public static boolean saveBoolean(String key, boolean value) {
        SharedPreferences sp = context.getSharedPreferences(name, mode);
        Editor edit = sp.edit();
        edit.putBoolean(key, value);
        return edit.commit();
    }

    public static boolean saveInt(String key, int value) {
        SharedPreferences sp = context.getSharedPreferences(name, mode);
        Editor edit = sp.edit();
        edit.putInt(key, value);
        return edit.commit();
    }

    public static boolean saveString(String key, String value) {
        SharedPreferences sp = context.getSharedPreferences(name, mode);
        Editor edit = sp.edit();
        edit.putString(key, value);
        return edit.commit();
    }

    public static boolean saveLong(String key, long value) {
        SharedPreferences sp = context.getSharedPreferences(name, mode);
        Editor edit = sp.edit();
        edit.putLong(key, value);
        return edit.commit();
    }


    /**
     * 获取首选项
     *
     * @param key
     * @param defValue
     * @return
     */
    public static boolean getBoolean(String key, boolean defValue) {
        SharedPreferences sp = context.getSharedPreferences(name, mode);
        return sp.getBoolean(key, defValue);
    }

    public static int getInt(String key, int defValue) {
        SharedPreferences sp = context.getSharedPreferences(name, mode);
        return sp.getInt(key, defValue);
    }

    public static String getString(String key, String defValue) {
        SharedPreferences sp = context.getSharedPreferences(name, mode);
        return sp.getString(key, defValue);
    }

    public static long getLong(String key, long defValue) {
        SharedPreferences sp = context.getSharedPreferences(name, mode);
        return sp.getLong(key, defValue);
    }

}
