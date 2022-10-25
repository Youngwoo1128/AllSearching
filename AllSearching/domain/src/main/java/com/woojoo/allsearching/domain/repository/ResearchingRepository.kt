package com.woojoo.allsearching.domain.repository

import com.woojoo.allsearching.domain.entites.Researching

interface ResearchingRepository {
    suspend fun getResearchingItem(): List<Researching>
    suspend fun insertResearching(item: Researching)
    suspend fun deleteResearching(item: Researching): Long?
}