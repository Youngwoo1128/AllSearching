package com.woojoo.allsearching.domain.usecases

import com.woojoo.allsearching.domain.entites.InsertResult
import com.woojoo.allsearching.domain.entites.Documents
import com.woojoo.allsearching.domain.repository.ResearchingRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class InsertResearchingUseCase @Inject constructor(
    private val researchingRepository: ResearchingRepository
) {
    suspend operator fun invoke(item: Documents): Flow<InsertResult> {
        return researchingRepository.insertResearching(item)
    }
}