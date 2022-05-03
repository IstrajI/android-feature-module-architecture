package com.nikitin.core.search.model

data class SearchResult<T: SearchItem> (
    val totalCount: Int,
    val isUncompleted: Boolean,
    val result: List<T>
)