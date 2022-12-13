package com.woojoo.allsearching.domain.repository

import androidx.paging.PagingData
import com.woojoo.allsearching.domain.ResponseResult
import com.woojoo.allsearching.domain.entites.Documents

interface SearchResultRepository {
    suspend fun getTotalList(query: String): ResponseResult
    suspend fun getTotalListLiveData(query: String): PagingData<Documents>
}