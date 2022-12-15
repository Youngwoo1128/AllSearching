package com.woojoo.allsearching.domain.entites

data class Error(
    val status: Int,
    val message: String,
    val throwable: Throwable
)
