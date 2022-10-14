package com.woojoo.allsearching.data.di

import com.woojoo.allsearching.data.datasources.SearchingDataSource
import com.woojoo.allsearching.data.datasources.SearchingDataSourceImpl
import com.woojoo.allsearching.data.network.NetworkAPI
import com.woojoo.allsearching.data.repository.SearchResultRepositoryImpl
import com.woojoo.allsearching.domain.repository.SearchResultRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object SearchingModule {

    @Provides
    fun provideSearchingDataSource(api: NetworkAPI): SearchingDataSource = SearchingDataSourceImpl(api)

    @Provides
    fun provideSearchResultRepository(
        searchingDataSource: SearchingDataSource,
        networkAPI: NetworkAPI
    ): SearchResultRepository = SearchResultRepositoryImpl(searchingDataSource, networkAPI)

}