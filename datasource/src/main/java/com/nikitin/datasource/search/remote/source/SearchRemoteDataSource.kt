package com.nikitin.datasource.search.remote.source

import com.nikitin.core.search.model.Repository
import com.nikitin.core.search.model.SearchResult

interface SearchRemoteDataSource {
    suspend fun searchRepository(name: String, page: Int = 1): Result<SearchResult<Repository>>
}