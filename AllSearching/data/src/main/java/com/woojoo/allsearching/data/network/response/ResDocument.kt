package com.woojoo.allsearching.data.network.response

data class ResDocument(
    //image
    val collection: String, //
    val thumbnail_url: String, // 미리보기 이미지 URL
    val image_url: String, // 이미지 URL v
    val width: Long, //이미지 가로 사이즈
    val height: Long, // 이미지 세로 사이즈
    val display_sitename: String, // 출처
    val doc_url: String, // 문서 URL

    //video
    val author: String,
    val play_time: Int,
    val thumbnail: String, //v
    val title: String,
    val url: String,

    //공통
    var datetime: String, // 문서 작성시간
    var viewType: Int
)