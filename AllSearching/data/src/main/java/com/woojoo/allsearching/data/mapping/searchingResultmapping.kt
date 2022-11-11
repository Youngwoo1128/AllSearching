package com.woojoo.allsearching.data.mapping

import android.util.Log
import com.woojoo.allsearching.data.network.response.ResDocument
import com.woojoo.allsearching.domain.entites.Documents
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.Comparator
import kotlin.collections.ArrayList


fun ArrayList<Documents>.combineDocument(): ArrayList<Documents> {

//    this.distinct()
    for (i in 0 until this.size) {
        this[i].datetime = convertDateToString(this[i].datetime.toString())
    }

    val sort = Comparator<Documents> { o1, o2 ->
        o2?.datetime?.compareTo(o1.datetime.toString())!!
    }

    Collections.sort(this, sort)
    return this
}

fun convertDateToString(dateString: String): String {
    Log.d("response Date", "$dateString")
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    val dateTime = OffsetDateTime.parse(dateString)
    return dateTime.format(formatter)
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
                    thumbnail = totalResponse[i].thumbnail_url,
                    url = totalResponse[i].doc_url
                )
            )
        } else {
            //video
            mappedModel.add(
                Documents(
                    datetime = totalResponse[i].datetime,
                    viewType = VIDEO_VIEW_TYPE,
                    title = totalResponse[i].title,
                    thumbnail = totalResponse[i].thumbnail,
                    url = totalResponse[i].url
                )
            )
        }
    }

    return mappedModel.combineDocument()
}

const val IMAGE_VIEW_TYPE = 1
const val VIDEO_VIEW_TYPE = 2