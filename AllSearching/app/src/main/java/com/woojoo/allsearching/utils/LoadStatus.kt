package com.woojoo.allsearching.utils

sealed class LoadStatus {
    object isLoading: LoadStatus()
    object loadFinish: LoadStatus()
}