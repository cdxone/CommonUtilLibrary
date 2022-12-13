package com.common.utils;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.IOException;
import java.io.InputStream;

public class AssetsUtils {

    /**
     * 从Assets/image中读取图片
     *
     * @param fileName 文件名
     */
    public static Bitmap getFlagImageFromAssets(Context context, String fileName) {
        Bitmap image = null;
        AssetManager am = context.getResources().getAssets();
        try {
            InputStream is = am.open("flags/" + fileName.toLowerCase() + "@3x.webp");
            image = BitmapFactory.decodeStream(is);
            is.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return image;
    }

}
