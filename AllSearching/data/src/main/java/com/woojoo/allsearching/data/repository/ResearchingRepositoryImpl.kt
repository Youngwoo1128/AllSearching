package com.woojoo.allsearching.data.repository

import com.woojoo.allsearching.data.local.ResearchingDao
import com.woojoo.allsearching.data.mapping.toData
import com.woojoo.allsearching.data.mapping.toDomain
import com.woojoo.allsearching.domain.entites.Researching
import com.woojoo.allsearching.domain.entites.ResponseResult
import com.woojoo.allsearching.domain.repository.ResearchingRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ResearchingRepositoryImpl @Inject constructor(
    private val researchingDao: ResearchingDao
) : ResearchingRepository {

    override suspend fun getResearchingList(): List<Researching> {
        return researchingDao.getAll().map {
            it.toDomain()
        }
    }

    override fun insertResearching(item: Researching): Flow<ResponseResult> {
        return flow<ResponseResult> {
            researchingDao.insertResearching(item.toData())
            emit(ResponseResult.ResultSuccess(item))
        }.catch { throwable ->
            emit(ResponseResult.ResultFail(throwable))
        }
    }

    override suspend fun deleteResearching(item: Researching): Int {
        researchingDao.delete(item.toData())
        return item.index.toInt()
    }

    override suspend fun updatePrimaryKey(item: Researching) {
        researchingDao.update(item.toData())
    }
}