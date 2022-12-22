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

    override suspend fun deleteItem(item: Researching): Flow<DeleteResult> {
        Log.e("ywsong", "1 item=$item")
        return flow {
            val researchingList = getResearchingList()
            val hashValue = if (item.id == null) deleteHashMap.getOrDefault(item.url, null)!! else item
            val result = deleteDataSource.getDeletedItemIndex(researchingList, hashValue)
            Log.e("ywsong", "3 item=$item")

            researchingDao.delete(hashValue.toData())
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

    /*중복 insert 방지*/
    override suspend fun isExistItem(item: Documents): Boolean {
        return checkOverlapDataSource.isExistItem(getResearchingList(), item)
    }

    /*
    * 즐겨찾기 하면 queue 에 저장
    * StorageFragment 가 onResume 상태가 되면 queue에 있는 것들을 pop 한 다음 notify
    * */
    override suspend fun addNotificationQueue(item: Documents) {
        notificationQueue.offer(item.toDomain())
    }

    /*
    * SearachingFragment 에서 즐겨찾기를 진행하고 StorageFragment 진입하여 삭제 가능하도록
    * insert 한 모델을 지우고 싶때는
    * */
    override suspend fun addDeleteHashMap(item: Documents) {
        val researchingList = getResearchingList()
        val researching = item.toDomain()
        researching.id = researchingList.last().id
        deleteHashMap[item.url] = researching
    }

}