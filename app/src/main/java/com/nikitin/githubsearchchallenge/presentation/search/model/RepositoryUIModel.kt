package com.nikitin.githubsearchchallenge.presentation.search.model

data class RepositoryUIModel(
    val id: Long,
    val name: String,
    val description: String?,
    val stars: String,
    val language: String?,
    val updated: String?,
    val licenseName: String?,
    val url: String
)