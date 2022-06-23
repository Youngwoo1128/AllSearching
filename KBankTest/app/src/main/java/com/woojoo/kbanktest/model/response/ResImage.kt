package com.woojoo.kbanktest.model.response

import com.woojoo.kbanktest.model.BaseResponse

data class ResImage(
    val meta: Meta,
    val documents: ArrayList<Document>
): BaseResponse()