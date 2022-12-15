package com.woojoo.allsearching.domain

import androidx.paging.PagingData
import com.woojoo.allsearching.domain.entites.Documents
import com.woojoo.allsearching.domain.entites.Error
import kotlinx.coroutines.flow.Flow

sealed class ResponseResult {
    class ResponseSuccess(val pagingData: Flow<PagingData<Documents>>): ResponseResult()
    class ResponseFail(val error: Error): ResponseResult()
}
