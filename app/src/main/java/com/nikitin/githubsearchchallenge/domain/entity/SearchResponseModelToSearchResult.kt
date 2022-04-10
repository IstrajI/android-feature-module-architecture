package com.nikitin.githubsearchchallenge.domain.entity

import com.nikitin.githubsearchchallenge.data.model.RepositoryModel
import com.nikitin.githubsearchchallenge.data.model.SearchResponseModel

class SearchResponseModelToSearchResult {
    //todo: here is a shit
    fun map(from: SearchResponseModel<RepositoryModel>): SearchResult {
        return from.run {
            SearchResult(totalCount = totalCount,
                isUncompleted = incompleteResults,
                result = items.map {
                    Repository(
                        id = it.id,
                        name = it.name,
                        description = it.description,
                        stars = it.stargazersCount,
                        language = it.language,
                        updated = it.updatedAt,
                        licenseName = it.license?.name,
                        url = it.url
                    )
                })
        }
    }
}