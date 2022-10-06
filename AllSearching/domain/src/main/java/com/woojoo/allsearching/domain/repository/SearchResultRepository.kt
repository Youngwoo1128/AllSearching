package com.woojoo.allsearching.domain.repository

import com.woojoo.allsearching.domain.entites.Documents

interface SearchResultRepository {
    suspend fun getTotalList(query: String, page: Int): ArrayList<Documents>
}