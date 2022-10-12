package com.woojoo.allsearching.data.repository

import com.woojoo.allsearching.data.datasources.SearchingDataSource
import com.woojoo.allsearching.data.mapping.searchingResultMapping
import com.woojoo.allsearching.domain.entites.Documents
import com.woojoo.allsearching.domain.repository.SearchResultRepository
import javax.inject.Inject

class SearchResultRepositoryImpl @Inject constructor(
    private val searchingDataSource: SearchingDataSource
) : SearchResultRepository {

    override suspend fun getTotalList(query: String, page: Int): ArrayList<Documents> {
        val responseImage = searchingDataSource.getImageResult(query, page, REQUEST_PARAM_SIZE).documents
        val responseVideo = searchingDataSource.getVideoResult(query, page, REQUEST_PARAM_SIZE).documents

        return searchingResultMapping(responseImage, responseVideo)
    }

    companion object {
        private const val REQUEST_PARAM_SIZE = 30
    }
}