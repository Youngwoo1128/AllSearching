package com.woojoo.allsearching.data.mapping

import android.util.Log
import com.woojoo.allsearching.data.network.response.ResDocument
import com.woojoo.allsearching.domain.entites.Documents
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter
import kotlin.collections.ArrayList


fun ArrayList<Documents>.sortDateTime(): ArrayList<Documents> {
    this.sortByDescending {
        it.datetime
    }
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
        if (!totalResponse[i].thumbnailUrl.isNullOrEmpty()) {
            //image
            mappedModel.add(
                Documents(
                    datetime = convertDateToString(totalResponse[i].datetime),
                    viewType = IMAGE_VIEW_TYPE,
                    title = totalResponse[i].collection ?: "",
                    thumbnail = totalResponse[i].thumbnailUrl ?: "",
                    url = totalResponse[i].doc_url ?: ""
                )
            )
            continue
        }
        //video
        mappedModel.add(
            Documents(
                datetime = convertDateToString(totalResponse[i].datetime),
                viewType = VIDEO_VIEW_TYPE,
                title = totalResponse[i].title ?: "",
                thumbnail = totalResponse[i].thumbnail ?: "",
                url = totalResponse[i].url ?: ""
            )
        )

    }

    return mappedModel.sortDateTime()
}

const val IMAGE_VIEW_TYPE = 1
const val VIDEO_VIEW_TYPE = 2