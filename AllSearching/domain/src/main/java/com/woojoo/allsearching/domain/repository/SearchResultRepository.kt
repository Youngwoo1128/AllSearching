package com.woojoo.allsearching.domain.repository

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.woojoo.allsearching.domain.entites.Documents
import kotlinx.coroutines.flow.Flow

interface SearchResultRepository {
    suspend fun getTotalList(query: String): Flow<PagingData<Documents>>

    suspend fun getTotalListLiveData(query: String): PagingData<Documents>
}