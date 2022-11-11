package com.woojoo.allsearching.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface ResearchingDao {

    @Query("SELECT * FROM ${ResearchingEntity.TABLE_NAME}")
    suspend fun getAll(): List<ResearchingEntity>

    // 중복 insert 방지 : 현재 문제 : 동일한 값을 Insert 해도 Insert 가 가능함
    // 이유 -> onConflict 의 기준은 다름 아닌 Id 었다,,,
    // 내용은 같아도,,, id만 다르기 때문 이었다,,
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertResearching(item: ResearchingEntity)

//    @Query("DELETE FROM ${ResearchingEntity.TABLE_NAME} WHERE id = :${researchingEntity.id}")
//    suspend fun delete(researchingEntity: ResearchingEntity): Int

    @Query("DELETE FROM ${ResearchingEntity.TABLE_NAME} WHERE id = :id")
    suspend fun delete(id: Int): Int

    @Update
    suspend fun update(researchingEntity: ResearchingEntity)
}