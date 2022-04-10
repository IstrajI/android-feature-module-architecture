package com.nikitin.githubsearchchallenge.feature.repositorysearch.entity

import com.nikitin.githubsearchchallenge.domain.entity.SearchResult

class GitHubSearchResponseToSearchResultUIModel {
    fun map(from: SearchResult): SearchResultUIModel {
        return from.run {
            SearchResultUIModel(
                totalCount = totalCount,
                isUncompleted = isUncompleted,
                result = result.map {
                    RepositoryUIModel(
                        id = it.id,
                        name = it.name,
                        description = it.description,
                        stars = it.stars,
                        language = it.language,
                        updated = it.updated,
                        licenseName = it.licenseName,
                        url = it.url
                    )
                }
            )
        }
    }
}