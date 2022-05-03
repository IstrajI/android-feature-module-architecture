package com.nikitin.ui_search.model

import com.nikitin.ui_search.repository.model.RepositoryUIModel

data class SearchResultUIModel(
    val totalCount: Int,
    val isUncompleted: Boolean,
    val result: List<RepositoryUIModel>
)