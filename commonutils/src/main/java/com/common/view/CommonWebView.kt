package com.common.view

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient

class CommonWebView : WebView {

    private var mCallBack: (() -> Unit)? = null
    private val client: WebViewClient = object : WebViewClient() {
        override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
            view.loadUrl(url)
            return true
        }

        override fun onPageFinished(p0: WebView?, p1: String?) {
            super.onPageFinished(p0, p1)
            mCallBack?.invoke()
        }
    }

    constructor(context: Context) : super(context) {
        init(context, null)
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init(context, attrs)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) :
            super(context, attrs, defStyleAttr) {
        init(context, attrs)
    }

    private fun init(context: Context, attrs: AttributeSet?) {
        this.webViewClient = client
        // this.setWebChromeClient(chromeClient);
        // WebStorage webStorage = WebStorage.getInstance();
        initWebViewSettings();
        //this.view.isClickable = true
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun initWebViewSettings() {
        val webSetting: WebSettings = this.settings
        webSetting.javaScriptEnabled = true
        //webSetting.setJavaScriptCanOpenWindowsAutomatically(true)
//        webSetting.allowFileAccess = true
//        webSetting.layoutAlgorithm = WebSettings.LayoutAlgorithm.NARROW_COLUMNS
        webSetting.setSupportZoom(true)
        //webSetting.builtInZoomControls = false
//        //webSetting.useWideViewPort = true
//        webSetting.setSupportMultipleWindows(true)
//        // webSetting.setLoadWithOverviewMode(true);
//        // webSetting.setLoadWithOverviewMode(true);
//        // webSetting.setAppCacheEnabled(true)
//        // webSetting.setDatabaseEnabled(true);
//        // webSetting.setDatabaseEnabled(true);
//        webSetting.domStorageEnabled = true
        webSetting.setGeolocationEnabled(false)
//        // webSetting.setAppCacheMaxSize(Long.MAX_VALUE)
//        // webSetting.setPageCacheCapacity(IX5WebSettings.DEFAULT_CACHE_CAPACITY);
//        // webSetting.setPageCacheCapacity(IX5WebSettings.DEFAULT_CACHE_CAPACITY);
//        webSetting.pluginState = WebSettings.PluginState.ON_DEMAND
//        // webSetting.setRenderPriority(WebSettings.RenderPriority.HIGH);
//        // webSetting.setRenderPriority(WebSettings.RenderPriority.HIGH);
//        webSetting.cacheMode = WebSettings.LOAD_NO_CACHE
//
//        // this.getSettingsExtension().setPageCacheCapacity(IX5WebSettings.DEFAULT_CACHE_CAPACITY);//extension
//        // settings 的设计
    }

    fun setOnPageFinished(callBack: () -> Unit) {
        mCallBack = callBack
    }


}