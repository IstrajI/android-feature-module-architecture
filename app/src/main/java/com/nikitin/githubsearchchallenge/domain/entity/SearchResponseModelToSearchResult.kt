package com.nikitin.githubsearchchallenge.domain.entity

import com.nikitin.githubsearchchallenge.data.model.RepositoryModel
import com.nikitin.githubsearchchallenge.data.model.SearchResponseModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SearchResponseModelToSearchResult @Inject constructor() {
    //todo: here is a shit
    fun map(from: SearchResponseModel<RepositoryModel>): SearchResult {
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