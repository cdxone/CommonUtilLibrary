package com.common.utils

import android.app.Activity
import android.app.Application.ActivityLifecycleCallbacks
import android.os.Bundle

class MyLifeCycle : ActivityLifecycleCallbacks {

    val activitys = mutableListOf<Activity>();
    var isFront = true //是否在前台 此变量有问题
    var beforeActivity = ""
    var beforeAllActivity = ""
    val TAG = "MyLifeCycle"
    var topActivity = ""

    override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
        activitys.add(activity)
    }

    override fun onActivityStarted(activity: Activity) {
    }

    override fun onActivityResumed(activity: Activity) {
        topActivity = activity::class.java.simpleName
        if (!isFront) {
            isFront = true
            // 热启动时，此时显示插页广告
            val simpleName = activity::class.java.simpleName
            LogUtils.i(TAG, "go to font,activity:$simpleName")
        }
    }

    override fun onActivityPaused(activity: Activity) {
        beforeActivity = activity::class.java.simpleName
        beforeAllActivity = activity::class.java.name
    }

    override fun onActivityStopped(activity: Activity) {
        if (!AppUtils.isAppOnForeground(activity)
            || AppUtils.isApplicationBroughtToBackground(activity)
        ) {
            LogUtils.i(TAG, "go to back")
            isFront = false
        }
    }

    override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {
    }

    override fun onActivityDestroyed(activity: Activity) {
        activitys.remove(activity)
    }
}