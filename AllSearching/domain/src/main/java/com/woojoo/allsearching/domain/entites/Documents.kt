package com.woojoo.allsearching.domain.entites

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Documents(
    var datetime: String,
    var viewType: Int,
    val title: String,
    val thumbnail: String,
    val url: String
): Parcelable