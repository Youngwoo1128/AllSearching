package com.woojoo.kbanktest.model.response

import com.woojoo.kbanktest.model.BaseResponse

/**
 * Class: ResVideo
 * Created by ywsong on 2022/06/23.
 * Description:
 */

data class ResVideo(
    val meta: Meta,
    val documents: ArrayList<Document>,
): BaseResponse()