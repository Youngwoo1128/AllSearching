package com.woojoo.allsearching.data.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class HeaderInterceptor @Inject constructor(
    val context: Context
) : Interceptor {

    private fun hasNetwork(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = connectivityManager.activeNetworkInfo
        return activeNetwork != null && activeNetwork.isConnected
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val headerInfo = if (hasNetwork(context)) {
            request
                .newBuilder()
                .header("Cache-control", "public, max-age=$CACHING_TIME") // 5초
                .header("Host", "dapi.kakao.com")
                .header("Authorization", "KakaoAK $KAKAO_REST_API_KEY")
                .build()
        } else {
            request
                .newBuilder()
                .header("Cache-control", "public, only-if-cached, max-stale=$CACHING_PERIOD") // 일주일
                .header("Host", "dapi.kakao.com")
                .header("Authorization", "KakaoAK $KAKAO_REST_API_KEY")
                .build()
        }

        return chain.proceed(headerInfo)
    }

    companion object {
        private const val KAKAO_REST_API_KEY = "97e6e3cc8b4ae9a058df4f56ccd8844b"
        private const val CACHING_TIME = 60 * 5 //5분
        private const val CACHING_PERIOD = 60 * 60 * 24 * 7 //1주일
    }

}