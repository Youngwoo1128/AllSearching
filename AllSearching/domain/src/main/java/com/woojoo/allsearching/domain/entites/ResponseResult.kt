package com.woojoo.allsearching.domain.entites

sealed class ResponseResult {
    data class ResultSuccess(val any: Any): ResponseResult()
    data class ResultFail(val throwable: Throwable? = null): ResponseResult()
}