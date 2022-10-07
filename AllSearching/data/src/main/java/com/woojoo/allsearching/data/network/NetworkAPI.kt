package com.woojoo.allsearching.data.network

import com.woojoo.allsearching.data.network.response.ResImage
import com.woojoo.allsearching.data.network.response.ResVideo
import retrofit2.http.GET
import retrofit2.http.Query


interface NetworkAPI {

    // 이미지 검색
    @GET("v2/search/image")
    suspend fun searchImageResult(
        @Query ("query") query: String,
        @Query("page") page: Int,
        @Query("size") size: Int
    ): ResImage

    @GET("/v2/search/vclip")
    suspend fun searchVideoResult(
        @Query("query") query: String,
        @Query("page") page: Int,
        @Query("size") size: Int
    ): ResVideo

}