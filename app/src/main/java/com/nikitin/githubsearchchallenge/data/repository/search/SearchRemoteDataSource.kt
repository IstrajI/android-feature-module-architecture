package com.nikitin.githubsearchchallenge.data.repository.search

import com.nikitin.githubsearchchallenge.data.model.Outcome
import com.nikitin.githubsearchchallenge.domain.entity.SearchResponseModelToSearchResult
import com.nikitin.githubsearchchallenge.domain.entity.SearchResult
import com.nikitin.githubsearchchallenge.repositories.GitHubAPI
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SearchRemoteDataSource @Inject constructor(private val api: GitHubAPI) {
    //todo provide mapper with inject
    //todo search result need to be typed
    suspend fun searchRepository(name: String, page: Int = 1): Outcome<SearchResult> {
        try {
            val response = api.searchRepository(name, page)
            if (!response.isSuccessful) return Outcome.ErrorOutcome(response.message())
            return Outcome.SuccessOutcome(SearchResponseModelToSearchResult().map(response.body()!!))
        } catch (ex: Exception) {
            return Outcome.ErrorOutcome(ex.message)
        }
    }
}