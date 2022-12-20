package com.woojoo.allsearching.data.datasources

import com.woojoo.allsearching.domain.entites.Documents
import com.woojoo.allsearching.domain.entites.Researching

interface CheckOverlapDataSource {
    fun isExistItem(list: List<Researching>, item: Documents): Boolean
}