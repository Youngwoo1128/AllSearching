package com.woojoo.allsearching.data.di

import com.woojoo.allsearching.data.datasources.SearchingDataSource
import com.woojoo.allsearching.data.datasources.SearchingDataSourceImpl
import com.woojoo.allsearching.data.network.NetworkAPI
import com.woojoo.allsearching.data.repository.SearchResultRepositoryImpl
import com.woojoo.allsearching.domain.repository.SearchResultRepository
import com.woojoo.allsearching.domain.usecases.SearchResultUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SearchingModule {

    @Provides
    fun provideSearchingDataSource(api: NetworkAPI): SearchingDataSource = SearchingDataSourceImpl(api)

    @Provides
    fun provideSearchResultRepository(
        searchingDataSource: SearchingDataSource
    ): SearchResultRepository = SearchResultRepositoryImpl(searchingDataSource)

}