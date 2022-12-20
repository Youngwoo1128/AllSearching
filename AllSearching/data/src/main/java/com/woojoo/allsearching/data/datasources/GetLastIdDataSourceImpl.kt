package com.woojoo.allsearching.data.datasources

import com.woojoo.allsearching.data.local.ResearchingEntity

class GetLastIdDataSourceImpl: GetLastIdDataSource {

    override fun getLastId(researchingList: List<ResearchingEntity>): Int {
        return when (researchingList.size) {
            0 -> 1
            else -> researchingList.last().id.toInt()
        }
    }

}