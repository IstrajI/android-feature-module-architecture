package com.nikitin.githubsearchchallenge.data.network

import com.nikitin.githubsearchchallenge.data.search.model.RepositoryModel
import com.nikitin.githubsearchchallenge.data.search.model.SearchResponseModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query


interface GitHubAPI {
    @Headers("Content-Type: application/json")
    @GET("search/repositories")
    suspend fun searchRepository(
        @Query("q") name: String,
        @Query("page") page: Int = 1,
    ): Response<SearchResponseModel<RepositoryModel>>
}