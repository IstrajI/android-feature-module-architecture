package com.nikitin.core.search.model

data class Repository(
    val id: Long,
    val name: String,
    val description: String?,
    val stars: Int,
    val language: String?,
    val updated: String,
    val licenseName: String?,
    val url: String
): SearchItem