package com.woojoo.allsearching.domain.usecases

import androidx.paging.PagingData
import com.woojoo.allsearching.domain.entites.Documents
import com.woojoo.allsearching.domain.repository.SearchResultRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchResultUseCase @Inject constructor(
    private val searchResultRepository: SearchResultRepository
){
    suspend operator fun invoke(query: String): Flow<PagingData<Documents>> {
         return searchResultRepository.getTotalList(query)
    }
}