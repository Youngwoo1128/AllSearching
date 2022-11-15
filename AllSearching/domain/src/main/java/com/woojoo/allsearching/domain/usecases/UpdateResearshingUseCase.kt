package com.woojoo.allsearching.domain.usecases

import android.util.Log
import com.woojoo.allsearching.domain.entites.Researching
import com.woojoo.allsearching.domain.repository.ResearchingRepository
import javax.inject.Inject

class UpdateResearchingUseCase @Inject constructor(
    private val researchingRepository: ResearchingRepository
){
    suspend operator fun invoke(item: Researching) {
        researchingRepository.updatePrimaryKey(item)
        Log.d("Update Primary Key", "${item.id}")
    }

}