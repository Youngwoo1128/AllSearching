package com.woojoo.allsearching.domain.usecases

import androidx.paging.PagingData
import com.woojoo.allsearching.domain.ResponseResult
import com.woojoo.allsearching.domain.entites.Documents
import com.woojoo.allsearching.domain.repository.SearchResultRepository
import javax.inject.Inject

class SearchResultUseCase @Inject constructor(
    private val searchResultRepository: SearchResultRepository
){
    suspend operator fun invoke(query: String): ResponseResult {
        return searchResultRepository.getTotalList(query)
    }

    suspend fun getSearchingResultLiveData(query: String): PagingData<Documents> {
        return searchResultRepository.getTotalListLiveData(query)
    }
}