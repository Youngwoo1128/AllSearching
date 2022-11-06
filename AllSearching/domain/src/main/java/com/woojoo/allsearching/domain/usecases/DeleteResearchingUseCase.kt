package com.woojoo.allsearching.domain.usecases

import com.woojoo.allsearching.domain.entites.Researching
import com.woojoo.allsearching.domain.repository.ResearchingRepository
import javax.inject.Inject

class DeleteResearchingUseCase @Inject constructor(
    private val researchingRepository: ResearchingRepository
) {

    private val getAllSearchResultUseCase = GetAllResearchingUseCase(researchingRepository)

    suspend operator fun invoke(item: Researching): Long {
        val primaryKey = item.id
        researchingRepository.deleteResearching(item)
        modifyLocalPrimaryKey(primaryKey!!.toInt())
        return primaryKey
    }

    private suspend fun modifyLocalPrimaryKey(deletedKey: Int) {
        val savedList = getAllSearchResultUseCase.invoke()

        for (i in savedList.size - 1 until deletedKey) {
            val indexValue = savedList[i]
            indexValue.id = (i - 1).toLong()
            UpdateResearchingUseCase(researchingRepository).invoke(indexValue)
        }

    }
}