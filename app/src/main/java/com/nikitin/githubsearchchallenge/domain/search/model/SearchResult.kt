package com.nikitin.githubsearchchallenge.domain.search.model


//todo: create full model
data class SearchResult<T: SearchItem> (
    val totalCount: Int,
    val isUncompleted: Boolean,
    //todo:parametrization
    val result: List<T>
)