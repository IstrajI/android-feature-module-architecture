package com.nikitin.githubsearchchallenge.domain.search.mapper

import com.nikitin.githubsearchchallenge.data.search.model.RepositoryModel
import com.nikitin.githubsearchchallenge.data.search.model.SearchResponseModel
import com.nikitin.githubsearchchallenge.domain.search.model.Repository
import com.nikitin.githubsearchchallenge.domain.search.model.SearchResult
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SearchResponseModelToSearchResult @Inject constructor() {
    fun mapRepositorySearch(from: SearchResponseModel<RepositoryModel>): SearchResult<Repository> {
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