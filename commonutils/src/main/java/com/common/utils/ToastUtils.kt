package com.common.utils;

import android.widget.Toast

class ToastUtils {

    companion object {
        fun toast(str: String) {
            Toast.makeText(GlobalContext.context,str,Toast.LENGTH_SHORT).show()
        }
    }
}