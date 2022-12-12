package com.common.utils

import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

open class JSONUtils {

    companion object {

        var TAG = "JSONUtils"

        /**
         * 将json格式字符串转换为List<对象>形式
         */
        inline fun <reified T> parseListFromJson(json: String) = parseObjectFromJson<List<T>>(json)

        /**
         * 将Json格式字符串转换为对象
         */
        inline fun <reified T> parseObjectFromJson(json: String): T? {
            return try {
                val type = object : TypeToken<T>() {}.type
                return Gson().fromJson(json, type)
            } catch (e: Exception) {
                Log.e(TAG, "解析json->obj出现错误,$e,$json")
                return null
            }
        }

        /**
         * 解析数据
         */
        inline fun <reified T> parseObjectFromJson(json: String, type: Class<T>): T? {
            return try {
                return Gson().fromJson(json, type)
            } catch (e: Exception) {
                return null
            }
        }

        /**
         * 将对象转换为JSON格式字符串
         */
        @JvmStatic
        fun toJsonFromObj(obj: Any?): String {
            var result = ""
            try {
                result = Gson().toJson(obj)
            } catch (e: Exception) {
                Log.e(TAG, "转换JSON失败：" + obj.toString() + "," + e.toString())
            }
            return result
        }
    }
}
