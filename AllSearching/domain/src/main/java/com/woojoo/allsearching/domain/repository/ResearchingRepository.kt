package com.woojoo.allsearching.domain.repository

import com.woojoo.allsearching.domain.entites.Researching
import com.woojoo.allsearching.domain.entites.ResponseResult
import kotlinx.coroutines.flow.Flow

interface ResearchingRepository {
    suspend fun getResearchingList(): List<Researching>
    suspend fun insertResearching(item: Researching): Flow<ResponseResult>

    suspend fun deleteResearching(item: Researching): Int
    suspend fun updatePrimaryKey(item: Researching)
}