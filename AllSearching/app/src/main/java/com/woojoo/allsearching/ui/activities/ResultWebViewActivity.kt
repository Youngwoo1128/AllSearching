package com.woojoo.allsearching.ui.activities

import android.annotation.SuppressLint
import android.os.Bundle
import android.webkit.WebSettings
import android.webkit.WebViewClient
import com.woojoo.allsearching.R
import com.woojoo.allsearching.constant.EXTRA_WEB_VIEW_MODEL
import com.woojoo.allsearching.databinding.ActivityWebViewBinding
import com.woojoo.allsearching.domain.entites.Documents
import com.woojoo.allsearching.ui.BindingActivity
import com.woojoo.allsearching.utils.ResultWebChromeClient
import com.woojoo.allsearching.utils.getParcelableValue

class ResultWebViewActivity : BindingActivity<ActivityWebViewBinding>(R.layout.activity_web_view) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initWebViewSetting()
        initView()
    }

    private fun initView() {
        val searchingData = intent?.extras?.getParcelableValue<Documents>(EXTRA_WEB_VIEW_MODEL)
        startWebBrowser(searchingData)

        binding.tvTitle.text = searchingData?.title

        binding.btnClose.setOnClickListener {
            finish()
        }

    }

    private fun startWebBrowser(searchingData: Documents?) {
        searchingData?.url?.let { url ->
            binding.webView.loadUrl(url)
        }
    }

    private fun initWebViewSetting() {
        with(binding.webView) {
            webViewClient = object : WebViewClient(){}
            webChromeClient = ResultWebChromeClient(this@ResultWebViewActivity)
            settings.setDefaultSetting()
            clearHistory()
            clearCache(true)
        }
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun WebSettings.setDefaultSetting() {
        javaScriptEnabled = true
        setSupportMultipleWindows(true)
        javaScriptCanOpenWindowsAutomatically = true
        useWideViewPort = true
        allowFileAccess = true
        domStorageEnabled = true
        setSupportZoom(false)
        cacheMode = WebSettings.LOAD_DEFAULT
        safeBrowsingEnabled = true
        loadWithOverviewMode = true
        textZoom = 100
    }
}