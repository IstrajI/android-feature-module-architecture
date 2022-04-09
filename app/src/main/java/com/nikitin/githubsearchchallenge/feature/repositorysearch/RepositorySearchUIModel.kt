package com.nikitin.githubsearchchallenge.feature.repositorysearch

data class RepositorySearchUIModel(
    val id: Long,
    val name: String,
    val description: String,
    val stars: Int,
    val language: String,
    val updated: String,
    val licenseName: String,
    val url: String
)