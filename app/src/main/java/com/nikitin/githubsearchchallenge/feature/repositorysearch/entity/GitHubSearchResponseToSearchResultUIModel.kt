package com.nikitin.githubsearchchallenge.feature.repositorysearch.entity

import com.nikitin.githubsearchchallenge.domain.entity.SearchResult
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GitHubSearchResponseToSearchResultUIModel @Inject constructor(
    private val numberFormatter: NumberFormatter,
    private val dateFormatter: DateFormatter
) {
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
                        stars = numberFormatter.shortFormat(it.stars),
                        language = it.language,
                        updated = dateFormatter.getUtcOffsetDateTime(it.updated),
                        licenseName = it.licenseName,
                        url = it.url
                    )
                }
            )
        }
    }
}