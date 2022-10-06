package com.woojoo.allsearching.data.datasources

import com.woojoo.allsearching.data.models.ResImage
import com.woojoo.allsearching.data.models.ResVideo

interface SearchingDataSource {
    suspend fun getImageResult(query: String, page: Int, size: Int): ResImage
    suspend fun getVideoResult(query: String, page: Int, size: Int): ResVideo
}