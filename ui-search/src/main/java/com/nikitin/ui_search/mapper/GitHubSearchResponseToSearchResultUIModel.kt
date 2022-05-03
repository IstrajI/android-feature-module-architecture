package com.nikitin.ui_search.mapper

import com.nikitin.ui_search.repository.model.RepositoryUIModel
import com.nikitin.ui_search.model.SearchResultUIModel
import com.nikitin.ui_components.formatter.DateFormatter
import com.nikitin.ui_components.formatter.NumberFormatter
import com.nikitin.core.search.model.Repository
import com.nikitin.core.search.model.SearchResult
import javax.inject.Inject
import javax.inject.Singleton

//todo: split into 2 mappers
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