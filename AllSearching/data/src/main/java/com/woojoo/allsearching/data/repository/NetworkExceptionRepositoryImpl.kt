package com.woojoo.allsearching.data.repository

import com.woojoo.allsearching.data.mapping.errorMapping
import com.woojoo.allsearching.data.network.response.NetworkError
import com.woojoo.allsearching.domain.entites.Error
import com.woojoo.allsearching.domain.repository.NetworkExceptionRepository
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

class NetworkExceptionRepositoryImpl: NetworkExceptionRepository {

    override fun getExceptionType(throwable: Throwable): Error {
        val errorMessage = throwable.message.toString()
        val errorReason = throwable.cause

        return when (errorReason) {
            is SocketTimeoutException -> NetworkError(SOCKET_TIME_OUT_EXCEPTION_STATUS, errorMessage, errorReason).errorMapping()
            is UnknownHostException -> NetworkError(UNKNOWN_HOST_EXCEPTION_STATUS, errorMessage, errorReason).errorMapping()
            is ConnectException -> NetworkError(CONNECT_EXCEPTION_STATUS, errorMessage, errorReason).errorMapping()
            else -> NetworkError(NORMAL_EXCEPTION_STATUS, errorMessage, Throwable()).errorMapping()
        }
    }


    companion object {
        private const val SOCKET_TIME_OUT_EXCEPTION_STATUS = 600
        private const val UNKNOWN_HOST_EXCEPTION_STATUS = 601
        private const val CONNECT_EXCEPTION_STATUS = 602
        private const val NORMAL_EXCEPTION_STATUS = 603
    }
}