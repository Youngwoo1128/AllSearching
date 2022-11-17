package com.woojoo.allsearching.extension

import android.util.Log
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

// 개선 해야할 점
// 1. exception handling 하는 것이 필요함
// 2. 사실 presentor 영역에 있을 필요가 없는 코드. 여러 레퍼런스를 찾아보고 연구해본 결과 clean Architecture 에 따르면
// data 영역에 들어가는 것이 맞다고 판단.
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