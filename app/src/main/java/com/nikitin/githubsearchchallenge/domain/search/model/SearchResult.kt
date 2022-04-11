package com.nikitin.githubsearchchallenge.domain.search.model

data class SearchResult<T: SearchItem> (
    val totalCount: Int,
    val isUncompleted: Boolean,
    val result: List<T>
)