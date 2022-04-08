package com.nikitin.githubsearchchallenge.repositories

import com.nikitin.githubsearchchallenge.data.model.GitHubRepositoryModel
import com.nikitin.githubsearchchallenge.data.model.GitHubSearchResponseModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query


interface GitHubAPI {
    @GET("search/repositories")
    suspend fun searchUser(
        @Query("q") name: String,
        @Query("sort") sort: String,
        @Query("order") order: String = "desc",
        @Query("per_page") perPage: Int = 30,
        @Query("page") page: Int = 10
    ): Response<String>

    @Headers("Content-Type: application/json")
    @GET("search/repositories")
    suspend fun searchRepository(
        @Query("q") name: String
    ): Response<GitHubSearchResponseModel<GitHubRepositoryModel>>
}