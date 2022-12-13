package com.common.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.provider.Settings;
import android.util.Log;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;

public class TokenUtils {

    private static String getUniqueId(Context context) {
        @SuppressLint("HardwareIds")
        // ANDROID_ID是设备第一次启动时产生和存储的64bit的一个数，当设备被wipe后该数重置。
        String androidID = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
        @SuppressLint("HardwareIds")
        String id = androidID + Build.SERIAL + Build.TIME; // +硬件序列号
        try {
            return toMD5For16(id);
        } catch (NoSuchAlgorithmException e) {
            LogUtils.e("getUniqueId error");
            return id;
        }
    }


    private static HashMap<String, String> getDesMsg(String appId) throws NoSuchAlgorithmException {
        //String time = Calendar.getInstance().getTimeInMillis() + "";
        String time = System.currentTimeMillis() + "";
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("appId", appId);
        hashMap.put("packageName", AppUtils.getPackageName());
        hashMap.put("deviceId", getUniqueId(GlobalContext.context));
        hashMap.put("timeStamp", time);
        return hashMap;
    }

    public static String getToken(String appId) {
        String s = "";
        try {
            s = JSONUtils.toJsonFromObj(getDesMsg(appId));
        } catch (Exception e) {
            LogUtils.e("getToken error");
        }
        Log.d("TOKEN_DS", s + "");
        return s;
    }

    private static String toMD5For16(String text) throws NoSuchAlgorithmException {
        //获取摘要器 MessageDigest
        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        //通过摘要器对字符串的二进制字节数组进行hash计算
        byte[] digest = messageDigest.digest(text.getBytes());
        StringBuilder sb = new StringBuilder();
        for (byte b : digest) {
            //循环每个字符 将计算结果转化为正整数;
            int digestInt = b & 0xff;
            //将10进制转化为较短的16进制
            String hexString = Integer.toHexString(digestInt);
            //转化结果如果是个位数会省略0,因此判断并补0
            if (hexString.length() < 2) {
                sb.append(0);
            }
            //将循环结果添加到缓冲区
            sb.append(hexString);
        }
        //返回整个结果
        return sb.toString().substring(8, 24);
    }

}