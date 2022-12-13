package com.common.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class TimeUtils {

    private static final long ONE_DAY = 24 * 60 * 60 * 1000;


    /**
     * 判断传入时间是否在当前偏移时间内
     * <p>
     * offset为偏移天数，0为当天
     *
     * @param time   时间字符串
     * @param offset 偏移天数
     */
    public static boolean containTime(String time, int offset) {
        long dayStartTimeMillis = parseDayTimeMillis(time);
        long dayEndTimeMillis = dayStartTimeMillis + ONE_DAY - 1;
        long dayParseTimeMillis = System.currentTimeMillis() + ONE_DAY * offset;
        return dayParseTimeMillis >= dayStartTimeMillis && dayParseTimeMillis <= dayEndTimeMillis;
    }

    /**
     * 格式化天时间戳
     *
     * @param time 时间字符串
     */
    public static long parseDayTimeMillis(String time) {
        return formatTime("yyyy-MM-dd", time);
    }

    /**
     * 格式化分钟时间戳
     *
     * @param time 时间字符串
     */
    public static long parseMinuteTimeMillis(String time) {
        return formatTime("yyyy-MM-dd HH:mm:ss", time);
    }

    /**
     * 格式化时间为时间戳
     *
     * @param pattern 时间格式
     * @param time    时间字符串
     */
    public static long formatTime(String pattern, String time) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern, Locale.CHINA);
        try {
            Date date = simpleDateFormat.parse(time);
            return date == null ? 0 : date.getTime();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static String format(long time) {
        long hour = 0;
        long min = 0;
        long second = 0;
        // 1825
        long hourDanWei = 3600 * 1000;
        long minDanWei = 60 * 1000;
        long sendDanWei = 1000;
        hour = (int) (time / hourDanWei);
        min = (time - hour * hourDanWei) / minDanWei;
        second = (time - hour * hourDanWei - min * minDanWei) / sendDanWei;
        String strHour = hour > 9 ? hour + "" : "0" + hour;
        String strMin = min > 9 ? min + "" : "0" + min;
        String strSecond = second > 9 ? second + "" : "0" + second;
        return strHour + ":" + strMin + ":" + strSecond;
    }
}
