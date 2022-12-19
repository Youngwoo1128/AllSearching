package com.woojoo.allsearching.domain.repository

import com.woojoo.allsearching.domain.entites.Researching
import com.woojoo.allsearching.domain.entites.DataBaseResult
import com.woojoo.allsearching.domain.entites.DeleteResult
import kotlinx.coroutines.flow.Flow

interface ResearchingRepository {
    suspend fun getResearchingList(): List<Researching>
    fun insertResearching(item: Researching): Flow<DataBaseResult>
    suspend fun deleteResearching(item: Researching): DeleteResult
}