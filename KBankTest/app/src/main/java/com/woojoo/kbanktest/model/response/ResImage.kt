package com.woojoo.kbanktest.model.response

data class ResImage(
    val meta: Meta,
    val documents: ArrayList<Documents>
)

data class Meta(
    val total_count: Int, //검색된 문서 수
    val pageable_count: Int, // total_count 중 볼 수 있는 문서 수
    val is_end: Boolean // 현재 페이지가 마지막 페이지인지 여부, false 면 page를 증가시켜 다음 페이지를 요청 가능함
)

data class Documents(
    val collection: String, //
    val thumbnail_url: String, // 미리보기 이미지 URL
    val image_url: String, // 이미지 URL
    val width: Long, //이미지 가로 사이즈
    val height: Long, // 이미지 세로 사이즈
    val display_sitename: String, // 출처
    val doc_url: String, // 문서 URL
    val datetime: String // 문서 작성시간
)