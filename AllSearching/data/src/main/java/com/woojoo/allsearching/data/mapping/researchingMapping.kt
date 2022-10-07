package com.woojoo.allsearching.data.mapping

import com.woojoo.allsearching.data.local.ResearchingEntity
import com.woojoo.allsearching.domain.entites.Researching

fun ResearchingEntity.toDomain() : Researching {
    return Researching(
        id = id,
        dateTime = dateTime,
        viewType = viewType,
        title = title,
        thumbnail = thumbnail
    )
}

fun Researching.toData(): ResearchingEntity {
    return ResearchingEntity(
        id = id,
        dateTime = dateTime,
        viewType = viewType,
        title = title,
        thumbnail = thumbnail
    )
}
