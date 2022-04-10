package com.nikitin.githubsearchchallenge.domain.entity

data class Repository(
    val id: Long,
    val name: String,
    val description: String?,
    val stars: Int,
    val language: String?,
    val updated: String,
    val licenseName: String?,
    val url: String
)