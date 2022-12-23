package com.woojoo.allsearching.domain.usecases

import com.woojoo.allsearching.domain.repository.ResearchingRepository
import javax.inject.Inject

class NotifyQueueUseCase @Inject constructor(
    private val researchingRepository: ResearchingRepository
) {

    operator fun invoke(): Boolean = researchingRepository.isNotifyQueueEmpty()
}