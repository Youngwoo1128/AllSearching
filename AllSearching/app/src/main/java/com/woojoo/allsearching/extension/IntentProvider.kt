package com.woojoo.allsearching.extension

import android.content.Context
import android.content.Intent
import com.woojoo.allsearching.constant.EXTRA_WEB_VIEW_MODEL
import com.woojoo.allsearching.domain.entites.Documents
import com.woojoo.allsearching.ui.activities.ResultWebViewActivity

object IntentProvider {

    fun getWebViewIntent(
        packageContext: Context,
        document: Documents
    ) = Intent(packageContext, ResultWebViewActivity::class.java).apply {
        putExtra(EXTRA_WEB_VIEW_MODEL, document)
    }
}