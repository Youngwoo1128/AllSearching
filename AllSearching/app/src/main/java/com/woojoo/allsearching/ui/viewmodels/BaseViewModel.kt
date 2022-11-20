package com.woojoo.allsearching.ui.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.woojoo.allsearching.domain.entites.ResError
import com.bumptech.glide.load.HttpException
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import java.net.SocketTimeoutException
import java.net.UnknownHostException

//android 에서 핸들링 가능한 Exception 종류를 알아보고 적용 후 data 영역으로 전환

open class BaseViewModel: ViewModel() {

    val networkException : LiveData<ResError>
        get() = _networkException
    private val _networkException = MutableLiveData<ResError>()

    fun CoroutineScope.requestAPI(
        loadingType: LoadingType = LoadingType.NormalLoading,
        exceptionHandle: (() -> Unit)? = null,
        exceptionInterceptor: ((Throwable) -> Boolean)? = null,
        block: suspend CoroutineScope.() -> Unit
    ) {

        val coroutineException = CoroutineExceptionHandler { _, throwable ->
            Log.d("Error : ", "${throwable.message}")
            throwable.printStackTrace()
            handlingError(throwable, exceptionInterceptor)
            exceptionHandle?.invoke()
        }

        this.launch (coroutineException) {
            block.invoke(this)
        }
    }

    private fun handlingError(throwable: Throwable, exceptionInterceptor: ((Throwable) -> Boolean)?) {
        if (exceptionInterceptor?.invoke(throwable) == true) return
        val message = throwable.message?.let { it } ?: "Exception Message"

        when (throwable) {
            is HttpException -> {

            }
            is SocketTimeoutException -> {
                // response 받을때 까지 시간 초과
                setNetworkException(SOCKET_TIME_OUT_EXCEPTION_STATUS, message)
            }
            is UnknownHostException -> {
                // 네트워크에 연결이 되지 않을 경우
                setNetworkException(UNKNOWN_HOST_EXCEPTION_STATUS, message)
            }
            is Exception -> {
                _networkException.value = ResError(NORMAL_EXCEPTION_STATUS, message)
            }
        }
    }

    private fun setNetworkException(status: Int, message: String) {
        _networkException.value = ResError(status, message)
    }

    sealed class LoadingType {
        object NormalLoading: LoadingType()
    }

    companion object {
        const val AUTHORIZATION_CHANGED_STATUS = 401
        const val SERVER_CHECKING_EXCEPTION_STATUS = 502
        const val SOCKET_TIME_OUT_EXCEPTION_STATUS = 600
        const val UNKNOWN_HOST_EXCEPTION_STATUS = 601
        const val CONNECT_EXCEPTION_STATUS = 602
        const val NORMAL_EXCEPTION_STATUS = 603
    }
}