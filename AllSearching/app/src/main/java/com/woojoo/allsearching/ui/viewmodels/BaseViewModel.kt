package com.woojoo.allsearching.ui.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

//android 에서 핸들링 가능한 Exception 종류를 알아보고 적용 후 data 영역으로 전환

open class BaseViewModel: ViewModel() {
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

        when (throwable) {

        }
    }


    protected fun handlingDatabaseError(throwable: Throwable) {
        val message = throwable.message.toString()
        Log.d("Database Error", "${message}")
    }

}