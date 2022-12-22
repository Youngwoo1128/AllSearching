package com.woojoo.allsearching.data.datasources

import com.woojoo.allsearching.domain.entites.Documents
import com.woojoo.allsearching.domain.entites.Researching

/*
* 중복방지에 관한 비즈니스 로직
* */
interface CheckOverlapDataSource {
    fun isExistItem(list: List<Researching>, item: Documents): Boolean
}