package com.woojoo.allsearching.data.datasources

import com.woojoo.allsearching.data.network.response.ResImage
import com.woojoo.allsearching.data.network.response.ResVideo
import com.woojoo.allsearching.data.network.NetworkAPI
import javax.inject.Inject

class SearchingDataSourceImpl @Inject constructor(
    private val api: NetworkAPI
) : SearchingDataSource {

    override suspend fun getImageResult(query: String, page: Int, size: Int): ResImage {
        return api.searchImageResult(query, page, size)
    }

    override suspend fun getVideoResult(query: String, page: Int, size: Int): ResVideo {
        return api.searchVideoResult(query, page, size)
    }
}