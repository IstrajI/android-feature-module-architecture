package com.nikitin.githubsearchchallenge.presentation.search.mapper

import com.nikitin.githubsearchchallenge.domain.search.model.Repository
import com.nikitin.githubsearchchallenge.domain.search.model.SearchResult
import com.nikitin.githubsearchchallenge.presentation.formatter.DateFormatter
import com.nikitin.githubsearchchallenge.presentation.formatter.NumberFormatter
import com.nikitin.githubsearchchallenge.presentation.search.model.RepositoryUIModel
import com.nikitin.githubsearchchallenge.presentation.search.model.SearchResultUIModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GitHubSearchResponseToSearchResultUIModel @Inject constructor(
    private val numberFormatter: NumberFormatter,
    private val dateFormatter: DateFormatter
) {
    fun mapRepositories(from: SearchResult<Repository>): SearchResultUIModel {
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