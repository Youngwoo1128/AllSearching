package com.woojoo.allsearching.domain.entites

sealed class DeleteResult {
    data class DeleteSuccess(val success: Int): DeleteResult()
    object DeleteFail: DeleteResult()
}
