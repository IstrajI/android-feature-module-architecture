package com.nikitin.githubsearchchallenge.domain.search.interactor

import com.nikitin.githubsearchchallenge.data.network.Outcome
import com.nikitin.githubsearchchallenge.data.search.repository.SearchRepository
import com.nikitin.githubsearchchallenge.domain.search.model.Repository
import com.nikitin.githubsearchchallenge.domain.search.model.SearchResult
import javax.inject.Inject

class SearchInteractor @Inject constructor(private val searchRepository: SearchRepository) {
    suspend fun searchRepository(name: String, page: Int): Outcome<SearchResult<Repository>> {
        return searchRepository.searchRepository(name = name, page = page)
    }
}