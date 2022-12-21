package com.woojoo.allsearching.domain.usecases

import com.woojoo.allsearching.domain.entites.Documents
import com.woojoo.allsearching.domain.repository.ResearchingRepository
import javax.inject.Inject

class CheckInsertUseCase @Inject constructor(
    private val researchingRepository: ResearchingRepository
) {

    suspend operator fun invoke(item: Documents) {
        researchingRepository.addNotificationQueue(item)
        researchingRepository.addDeleteHashMap(item)
    }
}