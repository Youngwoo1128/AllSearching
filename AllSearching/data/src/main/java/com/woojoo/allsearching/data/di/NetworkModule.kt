package com.woojoo.allsearching.data.di

import android.content.Context
import android.util.Log
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.woojoo.allsearching.data.network.HeaderInterceptor
import com.woojoo.allsearching.data.network.NetworkAPI
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private const val BASE_URL = "https://dapi.kakao.com/"

    @Singleton
    @Provides
    fun provideGsonDateFormatter(): Gson = GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm").create()

    @Singleton
    @Provides
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val logger = HttpLoggingInterceptor.Logger { message -> Log.d("Intercepter Message : ", message) }
        return HttpLoggingInterceptor(logger).apply {
            level = HttpLoggingInterceptor.Level.NONE
        }
    }


    @Singleton
    @Provides
    fun provideContext(
        @ApplicationContext context: Context
    ) = context

    @Singleton
    @Provides
    fun provideCacheFile(
        context: Context
    ) = Cache(context.cacheDir, (5 * 1024 * 1024).toLong())

    @Singleton
    @Provides
    fun provideHeaderInterceptor(
        context: Context
    ) = HeaderInterceptor(context)

    @Singleton
    @Provides
    fun provideOkhttpClient(
        logger: HttpLoggingInterceptor,
        headerInterceptor: HeaderInterceptor,
        cache: Cache
    ): OkHttpClient {
        return OkHttpClient.Builder().apply {
            cache(cache)
            addInterceptor(headerInterceptor)
            addInterceptor(logger)
        }.build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        gson: Gson
    ): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .build()
    }

    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit): NetworkAPI = retrofit.create(NetworkAPI::class.java)
}