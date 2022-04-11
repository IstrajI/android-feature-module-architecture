package com.nikitin.githubsearchchallenge.presentation.search.model

data class SearchResultUIModel(
    val totalCount: Int,
    val isUncompleted: Boolean,
    val result: List<RepositoryUIModel>
)