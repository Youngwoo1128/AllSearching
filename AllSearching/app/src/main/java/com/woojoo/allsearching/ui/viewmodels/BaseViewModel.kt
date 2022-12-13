package com.woojoo.allsearching.ui.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.woojoo.allsearching.domain.entites.Error
import com.bumptech.glide.load.HttpException
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.lang.Exception
import java.net.ConnectException

//android 에서 핸들링 가능한 Exception 종류를 알아보고 적용 후 data 영역으로 전환

open class BaseViewModel: ViewModel() {

    val networkException : LiveData<Error>
        get() = _networkException
    private val _networkException = MutableLiveData<Error>()

    val exceptionHandler: LiveData<Exception>
        get() = _exceptionHandler
    private val _exceptionHandler = MutableLiveData<Exception>()


    fun CoroutineScope.requestDataBase(
        exceptionHandle: (() -> Unit)? = null,
        exceptionInterceptor: ((Throwable) -> Boolean)? = null,
        block: suspend CoroutineScope.() -> Unit
    ) {

        val coroutineException = CoroutineExceptionHandler { _, throwable ->
            Log.d("DataBase Error : ", "${throwable.message}")
            throwable.printStackTrace()
            handlingDatabaseError(throwable, exceptionInterceptor)
            exceptionHandle?.invoke()
        }

        this.launch (coroutineException) {
            block.invoke(this)
        }
    }

    private fun handlingDatabaseError(throwable: Throwable, exceptionInterceptor: ((Throwable) -> Boolean)?) {
        /*
        * Database 작업의 에러들 핸들링하기
        * 어떤 에러가 발생할 수 있는지 조사하고 exception 처리
        * */

        if (exceptionInterceptor?.invoke(throwable) == true) return
        val message = throwable.message?.let { it } ?: "Exception Message"

        when (throwable) {

        }
    }


    protected fun handlingDatabaseError(throwable: Throwable) {
        val message = throwable.message.toString()
        Log.d("Database Error", "${message}")
    }

    protected fun handlingNetworkError(throwable: Throwable) {
        /*
        * HttpException 종류에 대해서 조사하고 에러 핸들링 해보기
        * */
        val message = throwable.message.toString()

        when (throwable) {
            is HttpException -> {}
            is SocketTimeoutException -> setNetworkException(SOCKET_TIME_OUT_EXCEPTION_STATUS, message)
            is UnknownHostException -> setNetworkException(UNKNOWN_HOST_EXCEPTION_STATUS, message)
            is ConnectException -> setNetworkException(CONNECT_EXCEPTION_STATUS, message)
            else -> setNetworkException(NORMAL_EXCEPTION_STATUS, message)
        }
    }

    private fun setNetworkException(status: Int, message: String) {
        _networkException.value = Error(status, message)
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