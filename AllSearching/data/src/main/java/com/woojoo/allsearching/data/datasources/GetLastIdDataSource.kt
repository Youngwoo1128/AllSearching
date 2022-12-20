package com.woojoo.allsearching.data.datasources

import com.woojoo.allsearching.data.local.ResearchingEntity

interface GetLastIdDataSource {
    fun getLastId(researchingList: List<ResearchingEntity>): Int
}