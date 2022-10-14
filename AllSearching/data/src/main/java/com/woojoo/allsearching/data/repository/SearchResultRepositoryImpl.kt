package com.woojoo.allsearching.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.woojoo.allsearching.data.datasources.SearchingDataSource
import com.woojoo.allsearching.data.network.NetworkAPI
import com.woojoo.allsearching.data.paging.SearchingPagingDataSource
import com.woojoo.allsearching.domain.entites.Documents
import com.woojoo.allsearching.domain.repository.SearchResultRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchResultRepositoryImpl @Inject constructor(
    private val searchingDataSource: SearchingDataSource,
    private val networkAPI: NetworkAPI
) : SearchResultRepository {

    override suspend fun getTotalList(query: String): Flow<PagingData<Documents>> {
        return Pager(
            config = PagingConfig(pageSize = 30, enablePlaceholders = false),
            pagingSourceFactory = { SearchingPagingDataSource(query, networkAPI) }
        ).flow
    }
}

/*
* PagingData를 구성하기 위해서는 이를 다른 앱 레이어에 전달하기 위한 API를 결정해야 한다.
* 여기서는 Flow를 사용해보겠다. 물론 RxJava 로도 가능하다.
* */

//class SearchResultRepositoryImpl @Inject constructor(
//    private val searchingDataSource: SearchingDataSource
//) : SearchResultRepository {
//
//    override suspend fun getTotalList(query: String, page: Int): ArrayList<Documents> {
//        val responseImage = searchingDataSource.getImageResult(query, page, REQUEST_PARAM_SIZE).documents
//        val responseVideo = searchingDataSource.getVideoResult(query, page, REQUEST_PARAM_SIZE).documents
//
//        return searchingResultMapping(responseImage, responseVideo)
//    }
//
//    companion object {
//        private const val REQUEST_PARAM_SIZE = 30
//    }
//}