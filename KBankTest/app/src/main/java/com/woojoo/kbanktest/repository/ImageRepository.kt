package com.woojoo.kbanktest.repository

import com.woojoo.kbanktest.network.NetworkAPI
import javax.inject.Inject

class ImageRepository @Inject constructor(private val api: NetworkAPI) {

    suspend fun getImageResult(query: String) = api.searchImageResult(query)
}