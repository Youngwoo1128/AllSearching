package com.woojoo.allsearching.data.mapping

import com.woojoo.allsearching.data.local.ResearchingEntity
import com.woojoo.allsearching.domain.entites.Researching

fun ResearchingEntity.toDomain() = Researching(
    id,
    index,
    dateTime,
    viewType,
    title,
    thumbnail,
    url.toString()
)

fun Researching.toData() = ResearchingEntity(
    id,
    index,
    dateTime,
    viewType,
    title,
    thumbnail,
    url
)
