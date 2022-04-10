package com.nikitin.githubsearchchallenge.data.repository.search

import com.nikitin.githubsearchchallenge.data.model.Outcome
import com.nikitin.githubsearchchallenge.domain.entity.SearchResult
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SearchRepository @Inject constructor(private val searchRemoteDataSource: SearchRemoteDataSource) {

    suspend fun searchRepository(name: String, page: Int = 1): Outcome<SearchResult> {
        return searchRemoteDataSource.searchRepository(name, page)
    }
}