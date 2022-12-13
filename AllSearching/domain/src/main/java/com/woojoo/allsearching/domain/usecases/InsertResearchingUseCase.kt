package com.woojoo.allsearching.domain.usecases

import com.woojoo.allsearching.domain.entites.Researching
import com.woojoo.allsearching.domain.entites.ResponseResult
import com.woojoo.allsearching.domain.repository.ResearchingRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class InsertResearchingUseCase @Inject constructor(
    private val researchingRepository: ResearchingRepository
) {
    operator fun invoke(item: Researching): Flow<ResponseResult> {
        return researchingRepository.insertResearching(item)
    }
}