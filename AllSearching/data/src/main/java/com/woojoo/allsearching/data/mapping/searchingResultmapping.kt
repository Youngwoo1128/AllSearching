package com.woojoo.allsearching.data.mapping

import com.woojoo.allsearching.data.network.response.ResDocument
import com.woojoo.allsearching.domain.entites.Documents
import java.util.*
import kotlin.Comparator
import kotlin.collections.ArrayList


fun ArrayList<Documents>.combineDocument(): ArrayList<Documents> {
    this.distinct()
    for (i in 0 until this.size) {
        this[i].datetime = this[i].datetime?.substring(0 until 10)
    }

    val sort = Comparator<Documents> { o1, o2 ->
        o2?.datetime?.compareTo(o1.datetime.toString())!!
    }

    Collections.sort(this, sort)
    return this
}

fun searchingResultMapping(
    imageResponse: ArrayList<ResDocument>,
    videoResponse: ArrayList<ResDocument>
): ArrayList<Documents> {

    val mappedModel = ArrayList<Documents>()
    val totalResponse = imageResponse + videoResponse

    for (i in totalResponse.indices) {
        if (!totalResponse[i].thumbnail_url.isNullOrEmpty()) {
            //image
            mappedModel.add(
                Documents(
                    datetime = totalResponse[i].datetime,
                    viewType = IMAGE_VIEW_TYPE,
                    title = totalResponse[i].collection,
                    thumbnail = totalResponse[i].thumbnail_url
                )
            )
        } else {
            //video
            mappedModel.add(
                Documents(
                    datetime = totalResponse[i].datetime,
                    viewType = VIDEO_VIEW_TYPE,
                    title = totalResponse[i].title,
                    thumbnail = totalResponse[i].thumbnail
                )
            )
        }
    }

    return mappedModel.combineDocument()
}

//fun ArrayList<ResDocument>.combineResponse(): ArrayList<Documents> {
//    val newArrayList = ArrayList<Documents>()
//    for (i in this.indices) {
//        val indexValue = this[i]
//        val item = Documents(
//            thumbnail = indexValue.thumbnail,
//            title = indexValue.title,
//            datetime = indexValue.datetime,
//            viewType = indexValue.viewType
//        )
//        newArrayList.add(item)
//    }
//    return newArrayList
//}

const val IMAGE_VIEW_TYPE = 1
const val VIDEO_VIEW_TYPE = 2