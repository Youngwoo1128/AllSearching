package com.woojoo.allsearching.data.network.response

import com.google.gson.annotations.SerializedName

data class ResDocument(
    //image
    @SerializedName("collection") val collection: String?, //
    @SerializedName("thumbnail_url") val thumbnailUrl: String?, // 미리보기 이미지 URL
    @SerializedName("image_url") val imageUrl: String?, // 이미지 URL v
    @SerializedName("width") val width: Long?, //이미지 가로 사이즈
    @SerializedName("height") val height: Long?, // 이미지 세로 사이즈
    @SerializedName("display_sitename") val displaySiteName: String?, // 출처
    @SerializedName("doc_url") val doc_url: String?, // 문서 URL

    //video
    @SerializedName("author") val author: String?,
    @SerializedName("play_time") val playTime: Int?,
    @SerializedName("thumbnail") val thumbnail: String?, //v
    @SerializedName("title") val title: String?,
    @SerializedName("url") val url: String?,


    //공통
    @SerializedName("datetime") var datetime: String, // 문서 작성시간
    var viewType: Int
)