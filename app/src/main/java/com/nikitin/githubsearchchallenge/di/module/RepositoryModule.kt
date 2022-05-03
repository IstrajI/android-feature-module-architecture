package com.nikitin.githubsearchchallenge.di.module

import com.nikitin.datasource.search.remote.source.SearchRemoteDataSource
import com.nikitin.datasource.search.remote.source.SearchRemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {
    @Provides
    @Singleton
    fun provideSearchRemoteDataSource(searchRemoteDataSource: SearchRemoteDataSourceImpl): SearchRemoteDataSource {
        return searchRemoteDataSource
    }
}