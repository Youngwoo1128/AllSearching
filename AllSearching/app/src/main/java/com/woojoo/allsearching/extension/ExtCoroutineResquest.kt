package com.woojoo.allsearching.extension

import android.util.Log
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

fun CoroutineScope.requestAPI(
    block: suspend CoroutineScope.() -> Unit
) {
    val coroutineException = CoroutineExceptionHandler { _, throwable ->
        Log.d("Error : ", "$throwable")
    }
    this.launch (coroutineException) {
        block.invoke(this)
    }
}