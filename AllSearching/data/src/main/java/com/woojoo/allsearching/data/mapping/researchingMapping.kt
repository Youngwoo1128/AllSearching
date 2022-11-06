package com.woojoo.allsearching.data.mapping

import com.woojoo.allsearching.data.local.ResearchingEntity
import com.woojoo.allsearching.domain.entites.Researching

fun ResearchingEntity.toDomain() = Researching(
    id,
    dateTime,
    viewType,
    title,
    thumbnail
)

fun Researching.toData() = ResearchingEntity(
    id,
    dateTime,
    viewType,
    title,
    thumbnail
)
