package com.woojoo.kbanktest

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class KBApplication: Application() {

    override fun onCreate() {
        super.onCreate()
    }
}