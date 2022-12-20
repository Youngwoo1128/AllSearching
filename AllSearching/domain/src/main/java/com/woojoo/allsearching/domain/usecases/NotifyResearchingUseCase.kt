package com.woojoo.allsearching.domain.usecases

import com.woojoo.allsearching.domain.entites.Researching
import com.woojoo.allsearching.domain.repository.ResearchingRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NotifyResearchingUseCase @Inject constructor(
    private val researchingRepository: ResearchingRepository
) {

    suspend operator fun invoke(): Flow<Researching> {
        return researchingRepository.notifyNewResearching()
    }
}