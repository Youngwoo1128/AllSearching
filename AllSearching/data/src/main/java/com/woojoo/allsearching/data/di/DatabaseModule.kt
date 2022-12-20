package com.woojoo.allsearching.data.di

import android.content.Context
import androidx.room.Room
import com.woojoo.allsearching.data.datasources.CheckOverlapDataSource
import com.woojoo.allsearching.data.datasources.CheckOverlapDataSourceImpl
import com.woojoo.allsearching.data.datasources.DeleteDataSource
import com.woojoo.allsearching.data.datasources.DeleteDataSourceImpl
import com.woojoo.allsearching.data.local.ResearchingDao
import com.woojoo.allsearching.data.local.ResearchingDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {

    @Singleton
    @Provides
    fun provideResearchingDatabase(
        @ApplicationContext context: Context
    ): ResearchingDatabase = Room
        .databaseBuilder(context, ResearchingDatabase::class.java, ResearchingDatabase.DATABASE_NAME)
        .fallbackToDestructiveMigration()
        .build()

    @Singleton
    @Provides
    fun provideResearchingDao(researchingDatabase: ResearchingDatabase): ResearchingDao = researchingDatabase.researchingDao()

    @Singleton
    @Provides
    fun provideDeleteDataSource(): DeleteDataSource = DeleteDataSourceImpl()

    @Singleton
    @Provides
    fun provideCheckOverlapDataSource(): CheckOverlapDataSource = CheckOverlapDataSourceImpl()
}