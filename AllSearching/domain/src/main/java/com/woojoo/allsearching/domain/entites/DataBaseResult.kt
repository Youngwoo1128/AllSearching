package com.woojoo.allsearching.domain.entites

sealed class DataBaseResult {
    data class ResultSuccess(val any: Any): DataBaseResult()
    data class ResultFail(val throwable: Throwable? = null): DataBaseResult()
}