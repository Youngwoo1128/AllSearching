package com.woojoo.allsearching.domain

import androidx.paging.PagingData
import com.woojoo.allsearching.domain.entites.Documents
import com.woojoo.allsearching.domain.entites.ResError
import kotlinx.coroutines.flow.Flow

sealed class ResponseResult {
    class ResponseSuccess(val pagingData: Flow<PagingData<Documents>>): ResponseResult()
    class ResponseFail(val error: ResError): ResponseResult()
}
