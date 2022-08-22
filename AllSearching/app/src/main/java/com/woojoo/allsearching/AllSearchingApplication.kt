package com.woojoo.allsearching

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class AllSearchingApplication: Application() {

    override fun onCreate() {
        super.onCreate()
    }
}