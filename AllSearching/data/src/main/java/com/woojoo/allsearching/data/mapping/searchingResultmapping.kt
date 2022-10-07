package com.woojoo.allsearching.data.mapping

import com.woojoo.allsearching.data.network.response.ResDocument
import com.woojoo.allsearching.domain.entites.Documents
import java.util.*
import kotlin.Comparator
import kotlin.collections.ArrayList


fun combineDocument(response: ArrayList<ResDocument>): ArrayList<Documents> {
    response.distinct()
    for (i in 0 until response.size) {
        response[i].datetime = response[i].datetime?.substring(0 until 10)
    }

    val sort = Comparator<ResDocument> { o1, o2 ->
        o2?.datetime?.compareTo(o1.datetime.toString())!!
    }

    Collections.sort(response, sort)
    return response.combineResponse()
}

fun ArrayList<ResDocument>.combineResponse() : ArrayList<Documents> {
    val newArrayList = ArrayList<Documents>()
    for (i in this.indices) {
        val indexValue = this[i]
        val item = Documents(
            collection = indexValue.collection,
            thumbnail = indexValue.thumbnail,
            thumbnail_url = indexValue.thumbnail_url,
            image_url = indexValue.image_url,
            width = indexValue.width,
            height = indexValue.height,
            display_sitename = indexValue.display_sitename,
            doc_url = indexValue.doc_url,
            author = indexValue.author,
            play_time = indexValue.play_time,
            title = indexValue.title,
            url = indexValue.url,
            datetime = indexValue.datetime,
            viewType = indexValue.viewType
        )
        newArrayList.add(item)
    }
    return newArrayList
}
