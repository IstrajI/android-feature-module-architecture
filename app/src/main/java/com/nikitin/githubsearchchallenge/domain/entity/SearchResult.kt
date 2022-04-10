package com.nikitin.githubsearchchallenge.domain.entity

//todo: create full model
data class SearchResult (
    val totalCount: Int,
    val isUncompleted: Boolean,
    //todo:parametrization
    val result: List<Repository>
)