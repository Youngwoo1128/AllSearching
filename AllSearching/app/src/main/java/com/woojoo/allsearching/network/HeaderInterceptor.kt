package com.woojoo.allsearching.network

import com.woojoo.allsearching.constant.KAKAO_REST_API_KEY
import okhttp3.Interceptor
import okhttp3.Response

class HeaderInterceptor : Interceptor{
    override fun intercept(chain: Interceptor.Chain): Response {
        val header = chain.request().newBuilder().apply {
            addHeader("Host", "dapi.kakao.com")
            addHeader("Authorization", "KakaoAK $KAKAO_REST_API_KEY")
        }
    return chain.proceed(header.build())
    }
}