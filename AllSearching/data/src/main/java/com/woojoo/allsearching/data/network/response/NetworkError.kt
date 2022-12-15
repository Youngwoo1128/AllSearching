package com.woojoo.allsearching.data.network.response

data class NetworkError (
    val status: Int,
    val message: String,
    val throwable: Throwable
        )