package com.woojoo.allsearching.data.network

import okhttp3.Interceptor
import okhttp3.Response

class HeaderInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val header = chain.request().newBuilder().apply {
            addHeader("Host", "dapi.kakao.com")
            addHeader("Authorization", "KakaoAK $KAKAO_REST_API_KEY")
        }
        return chain.proceed(header.build())
    }

    companion object {
        private const val KAKAO_REST_API_KEY = "97e6e3cc8b4ae9a058df4f56ccd8844b"
    }

}