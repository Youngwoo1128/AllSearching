package com.woojoo.allsearching.data.repository

import com.woojoo.allsearching.data.datasources.CheckOverlapDataSource
import com.woojoo.allsearching.data.datasources.DeleteDataSource
import com.woojoo.allsearching.data.local.ResearchingDao
import com.woojoo.allsearching.data.mapping.toData
import com.woojoo.allsearching.data.mapping.toDomain
import com.woojoo.allsearching.data.mapping.toEntity
import com.woojoo.allsearching.domain.entites.Researching
import com.woojoo.allsearching.domain.entites.InsertResult
import com.woojoo.allsearching.domain.entites.DeleteResult
import com.woojoo.allsearching.domain.entites.Documents
import com.woojoo.allsearching.domain.repository.ResearchingRepository
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class ResearchingRepositoryImpl @Inject constructor(
    private val researchingDao: ResearchingDao,
    private val deleteDataSource: DeleteDataSource,
    private val checkOverlapDataSource: CheckOverlapDataSource
) : ResearchingRepository {

    override suspend fun getResearchingList(): List<Researching> {
        return researchingDao.getAll().map {
            it.toDomain()
        }
    }

    override suspend fun insertResearching(item: Documents): Flow<InsertResult> {
        return flow<InsertResult> {
            researchingDao.insertResearching(item.toEntity())
            emit(InsertResult.ResultSuccess(item))
        }.catch { throwable ->
            emit(InsertResult.ResultFail(throwable))
        }
    }

    override suspend fun deleteResearching(item: Researching): Flow<DeleteResult> {
        return flow {
            val researchingList = getResearchingList()
            val result = deleteDataSource.getDeletedItemIndex(researchingList, item)
            researchingDao.delete(item.toData())
            emit(result)
        }
    }

    /*중복 insert 방지*/
    override suspend fun isExistItem(item: Documents): Boolean {
        return checkOverlapDataSource.isExistItem(getResearchingList(), item)
    }

}