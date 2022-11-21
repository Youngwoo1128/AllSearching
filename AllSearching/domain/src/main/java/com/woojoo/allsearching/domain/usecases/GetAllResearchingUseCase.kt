package com.woojoo.allsearching.domain.usecases

import com.woojoo.allsearching.domain.entites.Researching
import com.woojoo.allsearching.domain.repository.ResearchingRepository
import javax.inject.Inject

class GetAllResearchingUseCase @Inject constructor(
    private val researchingRepository: ResearchingRepository
) {
     suspend operator fun invoke(): List<Researching> {
        return researchingRepository.getResearchingList()
    }
}