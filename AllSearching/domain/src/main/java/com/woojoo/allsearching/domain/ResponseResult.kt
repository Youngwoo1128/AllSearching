package com.woojoo.allsearching.domain

import com.woojoo.allsearching.domain.entites.Documents

sealed class ResponseResult {
    class ResponseSuccess(documents: Documents): ResponseResult()
    class ResponseFail(error: Error): ResponseResult()
}
