package com.woojoo.allsearching.utils

import android.app.Activity
import android.content.Context
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.webkit.WebChromeClient
import android.widget.FrameLayout
import androidx.core.content.ContextCompat

class ResultWebChromeClient(
    webViewActivity: Activity
): WebChromeClient() {
    private val activity: Activity
        get() = _activity!!
    private var _activity: Activity? = null
    private var view: View? = null
    private var viewCallback: WebChromeClient.CustomViewCallback? = null
    private var orientation: Int = 0
    private var fullScreenContainer: FrameLayout? = null

    init {
        _activity = webViewActivity
    }

    override fun onShowCustomView(view: View?, callback: CustomViewCallback?) {
        if (view != null) {
            callback?.onCustomViewHidden()
            return
        }


        orientation = activity.requestedOrientation
        val decor = activity.window.decorView as? FrameLayout
        fullScreenContainer = FullScreenHolder(activity)
        fullScreenContainer?.addView(view, FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT))
        decor?.addView(fullScreenContainer, FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT))
        this.view = view
        setFullscreen(true)
        viewCallback = callback

        super.onShowCustomView(view, callback)
    }

//    override fun onShowCustomView(view: View?, requestedOrientation: Int, callback: CustomViewCallback?) {
//        super.onShowCustomView(view, requestedOrientation, callback)
//    }

    override fun onHideCustomView() {
        if (this.view == null) return

        setFullscreen(false)
        val decor = activity.window.decorView as? FrameLayout
        decor?.removeView(fullScreenContainer)
        fullScreenContainer = null
        view = null
        viewCallback?.onCustomViewHidden()
    }

    private fun setFullscreen(enabled: Boolean) {
        val win = activity.window
        val winParams = win.attributes
        val bits = WindowManager.LayoutParams.FLAG_FULLSCREEN
        if (enabled) {
            winParams.flags = winParams.flags or bits
        } else {
            winParams.flags = winParams.flags and bits.inv()
            if (view != null) {
                view!!.systemUiVisibility = View.SYSTEM_UI_FLAG_VISIBLE
            }
        }
        win.attributes = winParams
    }

    private inner class FullScreenHolder(context: Context): FrameLayout(context) {
        init {
            setBackgroundColor(ContextCompat.getColor(context, android.R.color.black))
        }

        override fun onTouchEvent(event: MotionEvent?): Boolean {
            return true
        }
    }
}