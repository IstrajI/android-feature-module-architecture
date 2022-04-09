package com.nikitin.githubsearchchallenge.domain

import com.nikitin.githubsearchchallenge.data.model.GitHubRepositoryModel
import com.nikitin.githubsearchchallenge.data.model.GitHubSearchResponseModel
import com.nikitin.githubsearchchallenge.data.repository.search.SearchRepository
import retrofit2.Response
import javax.inject.Inject

class SearchInteractor @Inject constructor(private val searchRepository: SearchRepository) {

    suspend fun searchRepository(name: String): Response<GitHubSearchResponseModel<GitHubRepositoryModel>> {
        return searchRepository.searchRepository(name)
    }
}