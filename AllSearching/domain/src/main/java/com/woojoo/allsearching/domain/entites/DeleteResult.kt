package com.woojoo.allsearching.domain.entites

sealed class DeleteResult {
    data class DeleteSuccess(val searchedIndex: Int): DeleteResult()
    object DeleteFail: DeleteResult()
}
