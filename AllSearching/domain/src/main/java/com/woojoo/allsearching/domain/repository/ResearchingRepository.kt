package com.woojoo.allsearching.domain.repository

import com.woojoo.allsearching.domain.entites.Researching
import com.woojoo.allsearching.domain.entites.InsertResult
import com.woojoo.allsearching.domain.entites.DeleteResult
import com.woojoo.allsearching.domain.entites.Documents
import kotlinx.coroutines.flow.Flow

interface ResearchingRepository {
    suspend fun getResearchingList(): List<Researching>
    suspend fun insertResearching(item: Documents): Flow<InsertResult>
    suspend fun deleteResearching(item: Researching): Flow<DeleteResult>
    suspend fun isExistItem(item: Documents): Boolean
}