package com.nikitin.githubsearchchallenge.feature.repositorysearch.entity

data class SearchResultUIModel(
    val totalCount: String,
    val isUncompleted: Boolean,
    val result: List<RepositoryUIModel>
)