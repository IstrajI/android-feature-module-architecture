package com.nikitin.githubsearchchallenge.data.repository.search

import com.nikitin.githubsearchchallenge.data.model.GitHubRepositoryModel
import com.nikitin.githubsearchchallenge.data.model.GitHubSearchResponseModel
import com.nikitin.githubsearchchallenge.repositories.GitHubAPI
import retrofit2.Response
import javax.inject.Inject

class SearchRemoteDataSource @Inject constructor(private val api: GitHubAPI) {

    suspend fun searchRepository(name: String): Response<GitHubSearchResponseModel<GitHubRepositoryModel>> {
        return api.searchRepository(name)
    }
}