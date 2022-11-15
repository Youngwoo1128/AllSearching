package com.woojoo.allsearching.domain.repository

import com.woojoo.allsearching.domain.entites.Researching

interface ResearchingRepository {
    suspend fun getResearchingList(): List<Researching>
    suspend fun insertResearching(item: Researching)

    suspend fun deleteResearching(item: Researching): Int
    suspend fun updatePrimaryKey(item: Researching)
}