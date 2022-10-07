package com.woojoo.allsearching.data.network.response

data class Meta(
    val total_count: Int, //검색된 문서 수
    val pageable_count: Int, // total_count 중 볼 수 있는 문서 수
    val is_end: Boolean // 현재 페이지가 마지막 페이지인지 여부, false 면 page를 증가시켜 다음 페이지를 요청 가능함
)
