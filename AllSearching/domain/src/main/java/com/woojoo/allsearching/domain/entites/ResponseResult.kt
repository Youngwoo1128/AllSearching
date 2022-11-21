package com.woojoo.allsearching.domain.entites

sealed class ResponseResult {
    object ResultSuccess: ResponseResult()
    object ResultFail: ResponseResult()
}