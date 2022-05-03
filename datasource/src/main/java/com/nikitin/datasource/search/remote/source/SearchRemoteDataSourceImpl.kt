package com.nikitin.datasource.search.remote.source

import com.nikitin.datasource.network.GitHubAPI
import com.nikitin.datasource.search.remote.mapper.RepositorySearchResponseMapper
import com.nikitin.core.search.model.Repository
import com.nikitin.core.search.model.SearchResult
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SearchRemoteDataSourceImpl @Inject constructor(
    private val api: GitHubAPI
) : SearchRemoteDataSource {
    override suspend fun searchRepository(name: String, page: Int): Result<SearchResult<Repository>> {
        val response = api.searchRepository(name, page)
        val body = response.body()

        return try {
            if (response.isSuccessful && body!= null) {
                //todo inject mapper
                Result.success(RepositorySearchResponseMapper().toRepositorySearch(body))
            } else {
                Result.failure(Exception(response.errorBody()?.string()?:""))
            }
        } catch (exception: IOException) {
            Result.failure(exception)
        }
    }
}