package com.nikitin.githubsearchchallenge.data.search.repository

import com.nikitin.githubsearchchallenge.data.network.Outcome
import com.nikitin.githubsearchchallenge.data.search.datasource.SearchRemoteDataSource
import com.nikitin.githubsearchchallenge.domain.search.model.Repository
import com.nikitin.githubsearchchallenge.domain.search.model.SearchResult
import com.nikitin.githubsearchchallenge.domain.search.repository.SearchRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SearchRepositoryImpl @Inject constructor(private val searchRemoteDataSource: SearchRemoteDataSource): SearchRepository {

    override suspend fun searchRepository(name: String, page: Int): Outcome<SearchResult<Repository>> {
        return searchRemoteDataSource.searchRepository(name, page)
    }
}