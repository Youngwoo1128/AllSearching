package com.woojoo.allsearching.domain.repository

import com.woojoo.allsearching.domain.entites.Error

interface NetworkExceptionRepository {
    fun getExceptionType(throwable: Throwable): Error
}