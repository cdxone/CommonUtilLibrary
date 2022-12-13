package com.common.utils;

import android.annotation.SuppressLint;
import android.net.ConnectivityManager;
import android.net.LinkProperties;
import android.text.TextUtils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.lang.reflect.Method;
import java.net.InetAddress;
import java.util.List;
import java.util.regex.Pattern;

public class IPTools {
    public static final String TAG = "IPTools";
    public static final String IP_SPLIT_REGEX_KEY = "==";
    private static final String IP_V4_ADDRESS_REGEX =
            "([1-9]|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])(\\.(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])){3}";
    private static final Pattern IP_V4_ADDRESS_PATTERN = Pattern.compile(IP_V4_ADDRESS_REGEX);

    public static boolean matches(final String ipAddress) {
        return IP_V4_ADDRESS_PATTERN.matcher(ipAddress).matches();
    }

    /**
     * 获取所有的IP,包括ipv4和macIp
     */
    @SuppressLint("PrivateApi")
    @Nullable
    public static String getIPAddress(@NonNull ConnectivityManager cm) {
        try {
            final Class<ConnectivityManager> cmClass = ConnectivityManager.class;
            @SuppressLint("SoonBlockedPrivateApi") final Method methodGetActiveLinkProperties = cmClass.getDeclaredMethod("getActiveLinkProperties");
            final LinkProperties linkProperties = (LinkProperties) methodGetActiveLinkProperties.invoke(cm);
            if (linkProperties == null) {
                return null;
            }
            final Class<LinkProperties> linkPropertiesClass = LinkProperties.class;
            final Method methodGetAllAddresses = linkPropertiesClass.getDeclaredMethod("getAllAddresses");
            final List<InetAddress> inetAddresses = (List<InetAddress>) methodGetAllAddresses.invoke(linkProperties);
            if (inetAddresses != null) {
                final StringBuilder stringBuilder = new StringBuilder();
                for (InetAddress inetAddress : inetAddresses) {
                    if (inetAddress != null) {
                        if (!TextUtils.isEmpty(stringBuilder)) {
                            stringBuilder.append(IP_SPLIT_REGEX_KEY);
                        }
                        stringBuilder.append(inetAddress.getHostAddress());
                    }
                }
                return stringBuilder.toString();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取当前IPV4
     */
    @Nullable
    public static String getIPAddressV4(@NonNull ConnectivityManager cm) {
        final String ipAddress = getIPAddress(cm);
        //Log.d(TAG, "getIPAddressV4: ipAddress = " + ipAddress);
        if (ipAddress != null) {
            final String[] ipArray = ipAddress.split(IP_SPLIT_REGEX_KEY);
            if (ipArray.length > 0) {
                for (String ip : ipArray) {
                    final String[] split = ip.split("\\.");
                    if (split.length == 4) {
                        return ip;
                    }
                }
            }
        }
        return null;
    }
}
