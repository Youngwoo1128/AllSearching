package com.woojoo.allsearching.domain.entites

sealed class InsertResult {
    data class ResultSuccess(val any: Any): InsertResult()
    data class ResultFail(val throwable: Throwable? = null): InsertResult()
}