package com.woojoo.allsearching.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter

@Database(
    entities = [ResearchingEntity::class],
    version = 1,
    exportSchema = false
)

abstract class ResearchingDatabase: RoomDatabase() {
    abstract fun researchingDao(): ResearchingDao

    companion object {
        const val DATABASE_NAME = "researching.db"
    }

}