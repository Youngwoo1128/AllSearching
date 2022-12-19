package com.woojoo.allsearching.data.datasources

import com.woojoo.allsearching.domain.entites.DeleteResult
import com.woojoo.allsearching.domain.entites.Researching

interface DeleteDataSource {
    fun getDeletedItemIndex(researchingList: List<Researching>, findItem: Researching): DeleteResult
}