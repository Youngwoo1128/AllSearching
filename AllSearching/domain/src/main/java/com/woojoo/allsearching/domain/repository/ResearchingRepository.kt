package com.woojoo.allsearching.domain.repository

import com.woojoo.allsearching.domain.entites.Researching
import com.woojoo.allsearching.domain.entites.DataBaseResult
import com.woojoo.allsearching.domain.entites.DeleteResult
import com.woojoo.allsearching.domain.entites.Documents
import kotlinx.coroutines.flow.Flow
import java.util.Deque

interface ResearchingRepository {
    suspend fun getResearchingList(): List<Researching>
    suspend fun insertResearching(item: Documents): Flow<DataBaseResult>
    suspend fun deleteResearching(item: Researching): Flow<DeleteResult>
    suspend fun notifyNewResearching(): Flow<Researching>
    suspend fun isExistItem(item: Documents): Boolean
}