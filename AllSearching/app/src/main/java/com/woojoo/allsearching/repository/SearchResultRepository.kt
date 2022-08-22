package com.woojoo.allsearching.repository

import com.woojoo.allsearching.network.NetworkAPI
import javax.inject.Inject

class SearchResultRepository @Inject constructor(private val api: NetworkAPI) {

    //이미지 불러오기
    suspend fun getImageResult(query: String, page: Int) = api.searchImageResult(query, page, 30)

    //비디오 불러오기
    suspend fun getVideoResult(query: String, page: Int) = api.searchVideoResult(query, page, 30)
}