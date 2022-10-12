package com.woojoo.allsearching.domain.entites

data class Documents(
    var datetime: String?,
    var viewType: Int,
    val title: String?,
    val thumbnail: String?,
)
