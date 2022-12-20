package com.woojoo.allsearching.data.datasources

import com.woojoo.allsearching.data.network.response.ResImage
import com.woojoo.allsearching.data.network.response.ResVideo

interface SearchingDataSource {
    suspend fun getImageResult(query: String, page: Int, size: Int): ResImage
    suspend fun getVideoResult(query: String, page: Int, size: Int): ResVideo
}