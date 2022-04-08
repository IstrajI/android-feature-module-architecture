package com.nikitin.githubsearchchallenge.repositories

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
    suspend fun searchUser2(
        @Query("q") name: String
    ): Response<String>
}