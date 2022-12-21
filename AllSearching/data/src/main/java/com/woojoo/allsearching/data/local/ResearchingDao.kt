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

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertResearching(item: ResearchingEntity)

    @Delete
    suspend fun delete(researchingEntity: ResearchingEntity)

//    @Query("DELETE FROM ${ResearchingEntity.TABLE_NAME} WHERE id = :id")
//    suspend fun delete(id: Long)


    @Update
    suspend fun update(researchingEntity: ResearchingEntity)
}