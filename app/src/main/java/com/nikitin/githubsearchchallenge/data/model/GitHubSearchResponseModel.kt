package com.nikitin.githubsearchchallenge.data.model

import com.google.gson.annotations.SerializedName

data class GitHubSearchResponseModel<T: IGitHubSearchResponseItemModel>(
    @SerializedName("total_count") var totalCount: Int? = null,
    @SerializedName("incomplete_results") var incompleteResults: Boolean? = null,
    @SerializedName("items") var items: ArrayList<T> = arrayListOf()
)