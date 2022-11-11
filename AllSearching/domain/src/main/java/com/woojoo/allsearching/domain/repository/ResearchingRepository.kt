package com.woojoo.allsearching.domain.repository

import com.woojoo.allsearching.domain.entites.Researching

interface ResearchingRepository {
    suspend fun getResearchingList(): List<Researching>
    suspend fun insertResearching(item: Researching)

//    suspend fun deleteResearching(item: Researching): Researching?

    suspend fun deleteResearching(id: Int): Int
    suspend fun updatePrimaryKey(item: Researching)
}