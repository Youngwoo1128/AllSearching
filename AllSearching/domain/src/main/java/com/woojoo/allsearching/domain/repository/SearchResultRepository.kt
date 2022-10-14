package com.woojoo.allsearching.domain.repository

import androidx.paging.Pager
import androidx.paging.PagingData
import com.woojoo.allsearching.domain.entites.Documents
import kotlinx.coroutines.flow.Flow

interface SearchResultRepository {
//    suspend fun getTotalList(query: String, page: Int): ArrayList<Documents>

    suspend fun getTotalList(query: String): Flow<PagingData<Documents>>
}