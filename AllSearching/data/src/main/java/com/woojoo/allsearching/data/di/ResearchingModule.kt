package com.woojoo.allsearching.data.di

import com.woojoo.allsearching.data.datasources.CheckOverlapDataSource
import com.woojoo.allsearching.data.datasources.DeleteDataSource
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
    fun provideResearchingDao(
        researchingDao: ResearchingDao,
        deleteDataSource: DeleteDataSource,
        checkOverlapDataSource: CheckOverlapDataSource
    ): ResearchingRepository = ResearchingRepositoryImpl(researchingDao, deleteDataSource, checkOverlapDataSource)

}