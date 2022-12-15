package com.woojoo.allsearching.ui.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.woojoo.allsearching.domain.entites.ResError
import com.bumptech.glide.load.HttpException
import com.woojoo.allsearching.domain.ResponseResult
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.lang.Exception
import java.net.ConnectException

//android 에서 핸들링 가능한 Exception 종류를 알아보고 적용 후 data 영역으로 전환

open class BaseViewModel: ViewModel() {

    val networkException : LiveData<ResError>
        get() = _networkException
    private val _networkException = MutableLiveData<ResError>()

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
        * 그리고 지금은 exception 핸들링을 ViewModel에서 작성하였는데 다른 layer에서 하도록 수정하기
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


    open fun handlingNetworkError(error: Throwable) {
        /*
        * HttpException 종류에 대해서 조사하고 에러 핸들링 해보기
        * */
        val errorMessage = error.message.toString()
        val exception = error.cause

        when (exception) {
            is HttpException -> {}
            is SocketTimeoutException -> setNetworkException(SOCKET_TIME_OUT_EXCEPTION_STATUS, errorMessage, exception)
            is UnknownHostException -> setNetworkException(UNKNOWN_HOST_EXCEPTION_STATUS, errorMessage, exception)
            is ConnectException -> setNetworkException(CONNECT_EXCEPTION_STATUS, errorMessage, exception)
            else -> setNetworkException(NORMAL_EXCEPTION_STATUS, errorMessage, exception!!)
        }
    }

    private fun setNetworkException(status: Int, message: String, throwable: Throwable) {
        Log.d("Network Exception", "status : $status message: ${throwable.message}")
        _networkException.value = ResError(message, throwable)
    }

    sealed class LoadingType {
        object NormalLoading: LoadingType()
    }

    companion object {
        const val SOCKET_TIME_OUT_EXCEPTION_STATUS = 600
        const val UNKNOWN_HOST_EXCEPTION_STATUS = 601
        const val CONNECT_EXCEPTION_STATUS = 602
        const val NORMAL_EXCEPTION_STATUS = 603
    }
}