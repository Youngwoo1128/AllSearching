package com.woojoo.allsearching.data.repository

import android.util.Log
import com.woojoo.allsearching.data.datasources.DeleteDataSource
import com.woojoo.allsearching.data.local.ResearchingDao
import com.woojoo.allsearching.data.mapping.toData
import com.woojoo.allsearching.data.mapping.toDomain
import com.woojoo.allsearching.domain.entites.Researching
import com.woojoo.allsearching.domain.entites.DataBaseResult
import com.woojoo.allsearching.domain.repository.ResearchingRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ResearchingRepositoryImpl @Inject constructor(
    private val researchingDao: ResearchingDao,
    private val deleteDataSource: DeleteDataSource
) : ResearchingRepository {

    override suspend fun getResearchingList(): List<Researching> {
        return researchingDao.getAll().map {
            it.toDomain()
        }
    }

    override fun insertResearching(item: Researching): Flow<DataBaseResult> {
        return flow<DataBaseResult> {
            researchingDao.insertResearching(item.toData())
            emit(DataBaseResult.ResultSuccess(item))
        }.catch { throwable ->
            emit(DataBaseResult.ResultFail(throwable))
        }
    }

    override suspend fun deleteResearching(item: Researching): Int {
        val researchingList = getResearchingList()
        val deletedId = deleteDataSource.getDeletedItemIndex(researchingList, item).toInt()
        Log.d("deletedId : ", "$deletedId")
        researchingDao.delete(item.toData())
        return deletedId
    }

}