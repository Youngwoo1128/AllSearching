package com.woojoo.allsearching.data.di

import com.woojoo.allsearching.data.repository.NetworkExceptionRepositoryImpl
import com.woojoo.allsearching.domain.repository.NetworkExceptionRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object NetworkExceptionModule {

    @Singleton
    @Provides
    fun provideNetworkExceptionRepository(): NetworkExceptionRepository = NetworkExceptionRepositoryImpl()

}