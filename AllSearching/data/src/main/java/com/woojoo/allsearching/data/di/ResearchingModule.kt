package com.woojoo.allsearching.data.di

import com.woojoo.allsearching.data.local.ResearchingDao
import com.woojoo.allsearching.data.repository.ResearchingRepositoryImpl
import com.woojoo.allsearching.domain.repository.ResearchingRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object ResearchingModule {

    @Singleton
    @Provides
    fun provideResearchingDao(researchingDao: ResearchingDao): ResearchingRepository = ResearchingRepositoryImpl(researchingDao)

}