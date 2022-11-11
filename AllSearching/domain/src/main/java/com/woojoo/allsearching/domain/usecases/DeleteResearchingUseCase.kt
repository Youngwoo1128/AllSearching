package com.woojoo.allsearching.domain.usecases

import com.woojoo.allsearching.domain.repository.ResearchingRepository
import javax.inject.Inject

class DeleteResearchingUseCase @Inject constructor(
    private val researchingRepository: ResearchingRepository
) {

    private val getAllSearchResultUseCase = GetAllResearchingUseCase(researchingRepository)
    private val updateResearchingUseCase = UpdateResearchingUseCase(researchingRepository)

//    suspend operator fun invoke(item: Researching): Researching {
//        val primaryKey = item.id
//        researchingRepository.deleteResearching(item)
//        modifyLocalPrimaryKey(primaryKey!!)
//        return item
//    }

    suspend operator fun invoke(id: Int): Int {
        return researchingRepository.deleteResearching(id)
    }

    private suspend fun modifyLocalPrimaryKey(deletedKey: Long) {
        val savedList = getAllSearchResultUseCase.invoke()

        for (i in deletedKey until savedList.size) {
            val roomData = savedList[i.toInt()]
            roomData.id = i - 1
            updateResearchingUseCase.invoke(roomData)
        }



    }
}