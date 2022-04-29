package com.woojoo.kbanktest.repository

import com.woojoo.kbanktest.network.NetworkAPI
import javax.inject.Inject

class ImageRepository @Inject constructor(private val api: NetworkAPI) {

    //이미지 불러오기
    suspend fun getImageResult(query: String, page: Int) = api.searchImageResult(query, page, 30)
}