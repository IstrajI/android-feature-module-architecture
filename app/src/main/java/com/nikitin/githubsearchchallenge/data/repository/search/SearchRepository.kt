package com.nikitin.githubsearchchallenge.data.repository.search

import com.nikitin.githubsearchchallenge.data.model.GitHubRepositoryModel
import com.nikitin.githubsearchchallenge.data.model.GitHubSearchResponseModel
import retrofit2.Response
import javax.inject.Inject

class SearchRepository @Inject constructor(private val searchRemoteDataSource: SearchRemoteDataSource) {

    suspend fun searchRepository(name: String): Response<GitHubSearchResponseModel<GitHubRepositoryModel>> {
        return searchRemoteDataSource.searchRepository(name)
    }
}