package com.woojoo.allsearching.domain.usecases

import android.util.Log
import com.woojoo.allsearching.domain.entites.DeleteResult
import com.woojoo.allsearching.domain.entites.Researching
import com.woojoo.allsearching.domain.repository.ResearchingRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DeleteResearchingUseCase @Inject constructor(
    private val researchingRepository: ResearchingRepository
) {

    suspend operator fun invoke(item: Researching): Flow<DeleteResult> {
        Log.d("item", "${item}")
        return researchingRepository.deleteResearching(item)

    }

}