package com.woojoo.allsearching.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.woojoo.allsearching.data.network.NetworkAPI
import com.woojoo.allsearching.data.paging.SearchingPagingDataSource
import com.woojoo.allsearching.domain.entites.Documents
import com.woojoo.allsearching.domain.repository.SearchResultRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchResultRepositoryImpl @Inject constructor(
    private val networkAPI: NetworkAPI
) : SearchResultRepository {

    override suspend fun getTotalList(query: String): Flow<PagingData<Documents>> {
        return Pager(
                config = PagingConfig(pageSize = REQUEST_PARAM_SIZE, enablePlaceholders = false),
                pagingSourceFactory = { SearchingPagingDataSource(query, networkAPI) }
            ).flow
        }


    companion object {
        private const val REQUEST_PARAM_SIZE = 30
    }
}