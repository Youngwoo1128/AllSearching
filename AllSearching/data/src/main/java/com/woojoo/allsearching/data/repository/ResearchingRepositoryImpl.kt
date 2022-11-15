package com.woojoo.allsearching.data.repository

import com.woojoo.allsearching.data.local.ResearchingDao
import com.woojoo.allsearching.data.mapping.toData
import com.woojoo.allsearching.data.mapping.toDomain
import com.woojoo.allsearching.domain.entites.Researching
import com.woojoo.allsearching.domain.repository.ResearchingRepository
import javax.inject.Inject

class ResearchingRepositoryImpl @Inject constructor(
    private val researchingDao: ResearchingDao
) : ResearchingRepository {

    override suspend fun getResearchingList(): List<Researching> {
        val researchingList = researchingDao.getAll()
        val list = mutableListOf<Researching>()
        for (i in researchingList.indices) {
            list.add(researchingList[i].toDomain())
        }
        return list
    }

    override suspend fun insertResearching(item: Researching) {
        researchingDao.insertResearching(item.toData())
    }

    override suspend fun deleteResearching(item: Researching): Int {
        researchingDao.delete(item.toData())
        return item.index!!.toInt()
    }

    override suspend fun updatePrimaryKey(item: Researching) {
        researchingDao.update(item.toData())
    }
}