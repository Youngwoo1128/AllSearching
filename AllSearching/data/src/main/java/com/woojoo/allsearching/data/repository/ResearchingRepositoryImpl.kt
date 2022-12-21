package com.woojoo.allsearching.data.repository

import android.util.Log
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
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import okhttp3.internal.wait
import java.util.Deque
import java.util.LinkedList
import javax.inject.Inject

class ResearchingRepositoryImpl @Inject constructor(
    private val researchingDao: ResearchingDao,
    private val deleteDataSource: DeleteDataSource,
    private val checkOverlapDataSource: CheckOverlapDataSource
) : ResearchingRepository {

    private val notificationQueue: Deque<Researching> = LinkedList()
    private val deleteHashMap = HashMap<String, Researching>()

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
            val result = if (item.id == null) {
                val hashValue = deleteHashMap.getOrDefault(item.url, null)
                deleteDataSource.getDeletedItemIndex(researchingList, hashValue!!)
            } else {
                deleteDataSource.getDeletedItemIndex(researchingList, item)
            }
            Log.d("deletedId : ", "$result")
            researchingDao.delete(item.toData())
            emit(result)
        }
    }

    override suspend fun notifyNewResearching(): Flow<Researching> {
        return flow {
            while (notificationQueue.size > 0) {
                val dequeue = notificationQueue.poll()
                delay(10L)
                if (dequeue != null) {
                    emit(dequeue)
                }
            }
        }
    }

    override suspend fun isExistItem(item: Documents): Boolean {
        return checkOverlapDataSource.isExistItem(getResearchingList(), item)
    }

    override suspend fun addNotificationQueue(item: Documents) {
        notificationQueue.offer(item.toDomain())
    }

    override suspend fun addDeleteHashMap(item: Documents) {
        val researchingList = getResearchingList()
        val researching = item.toDomain()
        researching.id = researchingList.last().id
        deleteHashMap[item.url] = researching
        Log.d("hashmap", "${deleteHashMap[item.url]}")
    }


//    private suspend fun getLastI

}