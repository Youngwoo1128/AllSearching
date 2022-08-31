package com.woojoo.allsearching.repository

import android.util.Log
import com.woojoo.allsearching.model.response.Document
import com.woojoo.allsearching.network.NetworkAPI
import java.util.*
import javax.inject.Inject
import kotlin.Comparator
import kotlin.collections.ArrayList

class Mapper @Inject constructor(private val api: NetworkAPI) {

    suspend fun getTotalList(query: String, page: Int): ArrayList<Document> {
        val imageList = api.searchImageResult(query, page, PARAM_REQUEST_LIMIT).documents
        val videoList = api.searchVideoResult(query, page, PARAM_REQUEST_LIMIT).documents

        return sortResponse((imageList + videoList) as ArrayList<Document>)
    }

    private fun sortResponse(result: ArrayList<Document>): ArrayList<Document> {
        result.distinct()

        // 문자열 자르기
        for (i in 0 until result.size) {
            result[i].datetime = result[i].datetime?.substring(0 until 10)
        }

        // 1. image의 dateTime과 video의 dataTime을 비교할 인터페이스 구현
        val sortObject = Comparator<Document> { o1, o2 ->
            o1?.datetime?.compareTo(o2?.datetime.toString())!!
        }

        // 2. 오름차순으로 정렬
        Collections.sort(result, sortObject)

        // 3. 내림차순으로 정렬
        result.reverse()

        Log.d("responseResult : ", "$result")
        return result
    }

    companion object {
        private const val PARAM_REQUEST_LIMIT = 30
    }
}