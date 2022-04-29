package com.woojoo.kbanktest.network

import com.woojoo.kbanktest.model.network.response.ResImage
import retrofit2.http.GET
import retrofit2.http.Query


interface NetworkAPI {

    // 이미지 검색
    @GET("v2/search/image")
    suspend fun searchImageResult(
        @Query ("query") query: String,
        @Query("page") page: Int,
        @Query("size") size: Int)
    : ResImage


}