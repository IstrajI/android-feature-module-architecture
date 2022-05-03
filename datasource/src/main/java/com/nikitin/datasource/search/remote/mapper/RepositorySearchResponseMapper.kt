package com.nikitin.datasource.search.remote.mapper

import com.nikitin.datasource.search.remote.model.RepositoryModel
import com.nikitin.datasource.search.remote.model.SearchResponseModel
import com.nikitin.core.search.model.Repository
import com.nikitin.core.search.model.SearchResult
import javax.inject.Inject
import javax.inject.Singleton

//todo split into 2
@Singleton
class RepositorySearchResponseMapper @Inject constructor() {
    fun toRepositorySearch(from: SearchResponseModel<RepositoryModel>): SearchResult<Repository> {
        return from.run {
            SearchResult(totalCount = totalCount,
                isUncompleted = incompleteResults,
                result = items.map {
                    Repository(
                        id = it.id,
                        name = it.fullName,
                        description = it.description,
                        stars = it.stargazersCount,
                        language = it.language,
                        updated = it.updatedAt,
                        licenseName = it.license?.spdxId,
                        url = it.htmlUrl
                    )
                })
        }
    }
}