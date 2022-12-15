package com.woojoo.allsearching.domain.usecases

import com.woojoo.allsearching.domain.entites.Error
import com.woojoo.allsearching.domain.repository.NetworkExceptionRepository
import javax.inject.Inject

class NetworkExceptionUseCase @Inject constructor(
    private val networkExceptionRepository: NetworkExceptionRepository
) {
    suspend operator fun invoke(throwable: Throwable): Error {
        return networkExceptionRepository.getExceptionType(throwable)
    }
}