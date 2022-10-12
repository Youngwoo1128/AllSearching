package com.woojoo.allsearching.data.mapping

import com.woojoo.allsearching.data.local.ResearchingEntity
import com.woojoo.allsearching.domain.entites.Researching

//fun ResearchingEntity.toDomain() : Researching {
//    if (this.viewType == IMAGE_VIEW_TYPE) {
//        return Researching(
//            id = id,
//            dateTime = dateTime,
//            viewType = viewType,
//            display_sitename = title,
//            thumbnail_url = thumbnail,
//            title = null,
//            thumbnail = null
//        )
//    } else {
//        return Researching(
//            id = id,
//            dateTime = dateTime,
//            viewType = viewType,
//            display_sitename = null,
//            thumbnail_url = null,
//            title = title,
//            thumbnail = thumbnail
//        )
//    }
//
//}

fun ResearchingEntity.toDomain() = Researching(
    id,
    dateTime,
    viewType,
    title,
    thumbnail
)


//fun Researching.toData(): ResearchingEntity {
//    if (this.viewType == IMAGE_VIEW_TYPE) {
//        return ResearchingEntity(
//            id = id,
//            dateTime = dateTime,
//            viewType = viewType,
//            title = display_sitename,
//            thumbnail = thumbnail_url
//        )
//    } else {
//        return ResearchingEntity(
//            id = id,
//            dateTime = dateTime,
//            viewType = viewType,
//            title = title,
//            thumbnail = thumbnail
//        )
//    }
//}

fun Researching.toData() = ResearchingEntity(
    id,
    dateTime,
    viewType,
    title,
    thumbnail
)
