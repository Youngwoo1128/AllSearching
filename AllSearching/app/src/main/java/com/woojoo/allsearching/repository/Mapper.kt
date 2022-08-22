package com.woojoo.allsearching.repository

import com.woojoo.allsearching.network.NetworkAPI
import javax.inject.Inject

/**
 * Class: Mapper
 * Created by ywsong on 2022/06/27.
 * Description:
 */

class Mapper @Inject constructor(private val api: NetworkAPI) {

    suspend fun getTotalList(query: String, page: Int) {
        val imageList = api.searchImageResult(query, page, 30)
        val videoList = api.searchVideoResult(query, page, 30)

    }

}