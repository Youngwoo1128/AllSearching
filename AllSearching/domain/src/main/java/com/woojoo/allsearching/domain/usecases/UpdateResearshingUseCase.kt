package com.woojoo.allsearching.domain.usecases

import com.woojoo.allsearching.domain.entites.Researching
import com.woojoo.allsearching.domain.repository.ResearchingRepository
import javax.inject.Inject

class UpdateResearchingUseCase @Inject constructor(
    private val researchingRepository: ResearchingRepository
){

    private val searchingList = GetAllResearchingUseCase(researchingRepository)
    private val updateItems = ArrayList<Researching>()

    suspend operator fun invoke(primaryKey: Int) {

    }



    /*
    * 1. 삭제할 primaryKey를 찾는다.
    * 2. 해당 primaryKey를 가진 아이템을 삭제한다.
    * 3. primaryKey뒤에 model의 id를 1씩 차감한다.
    * */


}