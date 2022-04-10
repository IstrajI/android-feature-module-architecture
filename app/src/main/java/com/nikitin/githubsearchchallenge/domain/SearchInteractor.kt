package com.nikitin.githubsearchchallenge.domain

import com.nikitin.githubsearchchallenge.data.model.Outcome
import com.nikitin.githubsearchchallenge.data.repository.search.SearchRepository
import com.nikitin.githubsearchchallenge.domain.entity.SearchResult
import javax.inject.Inject

class SearchInteractor @Inject constructor(private val searchRepository: SearchRepository) {
    suspend fun searchRepository(name: String, page: Int): Outcome<SearchResult> {
        return searchRepository.searchRepository(name = name, page = page)
    }
}