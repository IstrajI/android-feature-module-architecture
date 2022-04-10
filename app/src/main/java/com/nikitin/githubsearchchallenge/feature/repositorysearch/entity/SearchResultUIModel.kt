package com.nikitin.githubsearchchallenge.feature.repositorysearch.entity

data class SearchResultUIModel(
    val totalCount: Int,
    val isUncompleted: Boolean,
    val result: List<RepositoryUIModel>
)