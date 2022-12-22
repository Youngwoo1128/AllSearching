package com.woojoo.allsearching.data.mapping

import com.woojoo.allsearching.data.local.ResearchingEntity
import com.woojoo.allsearching.domain.entites.Documents
import com.woojoo.allsearching.domain.entites.Researching

fun ResearchingEntity.toDomain() = Researching(
    id,
    dateTime,
    viewType,
    title,
    thumbnail,
    url
)

fun Researching.toData() = ResearchingEntity(
    id = id,
    dateTime,
    viewType,
    title,
    thumbnail,
    url
)

fun Documents.toEntity() = ResearchingEntity(
    dateTime = datetime,
    viewType = viewType,
    title = title,
    thumbnail = thumbnail,
    url = url
)


fun Documents.toDomain() = Researching(
    id = null,
    dateTime = datetime,
    viewType = viewType,
    title = title,
    thumbnail = thumbnail,
    url = url
)