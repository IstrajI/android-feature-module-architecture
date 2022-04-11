package com.nikitin.githubsearchchallenge.domain.search.repository

import com.nikitin.githubsearchchallenge.data.network.Outcome
import com.nikitin.githubsearchchallenge.domain.search.model.Repository
import com.nikitin.githubsearchchallenge.domain.search.model.SearchResult

interface SearchRepository {
    suspend fun searchRepository(name: String, page: Int = 1): Outcome<SearchResult<Repository>>
}