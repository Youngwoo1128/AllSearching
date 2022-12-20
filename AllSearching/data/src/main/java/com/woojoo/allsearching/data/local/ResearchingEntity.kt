package com.woojoo.allsearching.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.woojoo.allsearching.data.local.ResearchingEntity.Companion.TABLE_NAME

@Entity(tableName = TABLE_NAME)
data class ResearchingEntity(
    @PrimaryKey(autoGenerate = true) var id: Long? = null,
    @ColumnInfo(name = "dateTime") val dateTime: String,
    @ColumnInfo(name = "viewType") val viewType: Int,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "thumbnail") val thumbnail: String,
    @ColumnInfo(name = "url") val url: String
) {
    companion object {
        const val TABLE_NAME = "researching"
    }
}
