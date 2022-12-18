package com.woojoo.allsearching.domain.entites

data class Researching(
    var id: Long?,
    val dateTime: String,
    val viewType: Int,
    val title: String,
    val thumbnail: String,
    val url: String
)
