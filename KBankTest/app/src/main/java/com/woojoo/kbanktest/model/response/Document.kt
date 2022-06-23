package com.woojoo.kbanktest.model.response

/**
 * Class: ResVideoDocument
 * Created by ywsong on 2022/06/23.
 * Description: request 이미지, 동영상
 */

data class Document(
    val collection: String?, //
    val thumbnail_url: String?, // 미리보기 이미지 URL
    val image_url: String?, // 이미지 URL
    val width: Long?, //이미지 가로 사이즈
    val height: Long?, // 이미지 세로 사이즈
    val display_sitename: String?, // 출처
    val doc_url: String?, // 문서 URL
    val datetime: String?, // 문서 작성시간

    val author: String?,
    val play_time: Int?,
    val thumbnail: String?,
    val title: String?,
    val url: String?,
)
