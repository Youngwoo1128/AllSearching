package com.woojoo.allsearching.data.repository

import android.util.Log
import com.woojoo.allsearching.data.datasources.DeleteDataSource
import com.woojoo.allsearching.data.datasources.GetLastIdDataSource
import com.woojoo.allsearching.data.local.ResearchingDao
import com.woojoo.allsearching.data.mapping.toData
import com.woojoo.allsearching.data.mapping.toDomain
import com.woojoo.allsearching.domain.entites.Researching
import com.woojoo.allsearching.domain.entites.DataBaseResult
import com.woojoo.allsearching.domain.entites.DeleteResult
import com.woojoo.allsearching.domain.repository.ResearchingRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import java.util.Deque
import java.util.LinkedList
import java.util.Queue
import javax.inject.Inject

class ResearchingRepositoryImpl @Inject constructor(
    private val researchingDao: ResearchingDao,
    private val deleteDataSource: DeleteDataSource,
    private val getLastIdDataSource: GetLastIdDataSource
) : ResearchingRepository {

    private val queue: Deque<Researching> = LinkedList()

    override suspend fun getResearchingList(): List<Researching> {
        return researchingDao.getAll().map {
            it.toDomain()
        }
    }

    override suspend fun insertResearching(item: Researching): Flow<DataBaseResult> {
        return flow<DataBaseResult> {
            researchingDao.insertResearching(item.toData())
            emit(DataBaseResult.ResultSuccess(item))
            queue.offer(item)
        }.catch { throwable ->
            emit(DataBaseResult.ResultFail(throwable))
        }
    }

    override suspend fun deleteResearching(item: Researching): Flow<DeleteResult> {
        return flow {
            val researchingList = getResearchingList()
            val result = deleteDataSource.getDeletedItemIndex(researchingList, item)
            Log.d("deletedId : ", "$result")
            researchingDao.delete(item.toData())
            emit(result)
        }
    }

    override suspend fun notifyNewResearching(): Flow<Researching> {
        return flow {
            while (queue.size > 0) {
                val dequeue = queue.poll()
                delay(10L)
                if (dequeue != null) {
                    emit(dequeue)
                }
            }
        }
    }

    override suspend fun getLastId(list: List<Researching>): Int {
        val mapList = list.map {
            it.toData()
        }
        return getLastIdDataSource.getLastId(mapList)
    }
}