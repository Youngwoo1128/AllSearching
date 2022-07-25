package com.woojoo.kbanktest.repository

import com.woojoo.kbanktest.network.NetworkAPI
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