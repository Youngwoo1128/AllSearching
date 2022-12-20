package com.woojoo.allsearching.data.datasources

import com.woojoo.allsearching.domain.entites.Documents
import com.woojoo.allsearching.domain.entites.Researching

class CheckOverlapDataSourceImpl: CheckOverlapDataSource {

    /*
    * 이번엔 선형검색으로 찾아보기
    * */

    override fun isExistItem(list: List<Researching>, item: Documents): Boolean {
        for (i in list) {
            if (i.title == item.title && i.thumbnail == item.thumbnail) {
                return true
            }
        }
        return false
    }
}