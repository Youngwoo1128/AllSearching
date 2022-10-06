package com.woojoo.allsearching.domain.usecases

import com.woojoo.allsearching.domain.entites.Documents
import com.woojoo.allsearching.domain.repository.SearchResultRepository
import javax.inject.Inject

class SearchResultUseCase @Inject constructor(
    private val searchResultRepository: SearchResultRepository
){
    suspend operator fun invoke(query: String, page: Int): ArrayList<Documents> {
        return searchResultRepository.getTotalList(query, page)
    }
}