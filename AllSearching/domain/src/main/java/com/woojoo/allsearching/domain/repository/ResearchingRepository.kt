package com.woojoo.allsearching.domain.repository

import com.woojoo.allsearching.domain.entites.Researching
import com.woojoo.allsearching.domain.entites.DataBaseResult
import kotlinx.coroutines.flow.Flow

interface ResearchingRepository {
    suspend fun getResearchingList(): List<Researching>
    fun insertResearching(item: Researching): Flow<DataBaseResult>
    suspend fun deleteResearching(item: Researching): Int
    suspend fun updateResearchingItem(item: Researching)
}