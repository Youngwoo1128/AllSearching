package com.woojoo.allsearching.model.response

/**
 * Class: ResVideo
 * Created by ywsong on 2022/06/23.
 * Description:
 */

data class ResVideo(
    val meta: Meta,
    val documents: ArrayList<Document>,
)