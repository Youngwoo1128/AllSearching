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



    /*
    * 1. 삭제할 primaryKey를 찾는다.
    * 2. 해당 primaryKey를 가진 아이템을 삭제한다.
    * 3. primaryKey뒤에 model의 id를 1씩 차감한다.
    * */


}