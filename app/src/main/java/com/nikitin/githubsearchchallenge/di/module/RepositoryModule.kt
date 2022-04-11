package com.nikitin.githubsearchchallenge.di.module

import com.nikitin.githubsearchchallenge.data.search.repository.SearchRepositoryImpl
import com.nikitin.githubsearchchallenge.domain.search.repository.SearchRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {
    @Provides
    @Singleton
    fun provideSearchRepository(searchRepositoryImpl: SearchRepositoryImpl): SearchRepository =
        searchRepositoryImpl

}