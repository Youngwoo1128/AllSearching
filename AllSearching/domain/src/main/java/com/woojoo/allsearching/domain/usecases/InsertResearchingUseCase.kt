package com.woojoo.allsearching.domain.usecases

import com.woojoo.allsearching.domain.entites.Researching
import com.woojoo.allsearching.domain.entites.DataBaseResult
import com.woojoo.allsearching.domain.repository.ResearchingRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class InsertResearchingUseCase @Inject constructor(
    private val researchingRepository: ResearchingRepository
) {
    suspend operator fun invoke(item: Researching): Flow<DataBaseResult> {
        return researchingRepository.insertResearching(item)
    }
}