package com.nikitin.githubsearchchallenge.data.search.datasource

import com.nikitin.githubsearchchallenge.data.network.Outcome
import com.nikitin.githubsearchchallenge.domain.search.mapper.SearchResponseModelToSearchResult
import com.nikitin.githubsearchchallenge.domain.search.model.SearchResult
import com.nikitin.githubsearchchallenge.data.network.GitHubAPI
import com.nikitin.githubsearchchallenge.data.search.model.RepositoryModel
import com.nikitin.githubsearchchallenge.domain.search.model.Repository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SearchRemoteDataSource @Inject constructor(
    private val api: GitHubAPI,
    private val searchResponseModelToSearchResult: SearchResponseModelToSearchResult
) {
    suspend fun searchRepository(name: String, page: Int = 1): Outcome<SearchResult<Repository>> {
        try {
            val response = api.searchRepository(name, page)
            if (!response.isSuccessful) return Outcome.ErrorOutcome(response.code().toString())
            return Outcome.SuccessOutcome(searchResponseModelToSearchResult.mapRepositorySearch(response.body()!!))
        } catch (ex: Exception) {
            return Outcome.ErrorOutcome(ex.message)
        }
    }
}