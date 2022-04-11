package com.nikitin.githubsearchchallenge.data.search.repository

import com.nikitin.githubsearchchallenge.data.network.Outcome
import com.nikitin.githubsearchchallenge.data.search.datasource.SearchRemoteDataSource
import com.nikitin.githubsearchchallenge.domain.search.model.Repository
import com.nikitin.githubsearchchallenge.domain.search.model.SearchResult
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SearchRepository @Inject constructor(private val searchRemoteDataSource: SearchRemoteDataSource) {

    suspend fun searchRepository(name: String, page: Int = 1): Outcome<SearchResult<Repository>> {
        return searchRemoteDataSource.searchRepository(name, page)
    }
}