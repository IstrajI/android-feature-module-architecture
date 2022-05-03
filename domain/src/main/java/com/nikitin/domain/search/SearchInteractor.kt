package com.nikitin.domain.search

import com.nikitin.datasource.search.remote.source.SearchRemoteDataSource
import com.nikitin.core.search.model.Repository
import com.nikitin.core.search.model.SearchResult
import javax.inject.Inject

class SearchInteractor @Inject constructor(private val searchRemoteDataSource: SearchRemoteDataSource) {
    suspend fun searchRepository(name: String, page: Int): Result<SearchResult<Repository>> {
        return searchRemoteDataSource.searchRepository(name = name, page = page)
    }
}