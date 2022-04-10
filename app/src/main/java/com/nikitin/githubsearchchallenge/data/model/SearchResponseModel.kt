package com.nikitin.githubsearchchallenge.data.model

import com.google.gson.annotations.SerializedName

data class SearchResponseModel<T: SearchResponseItemModel>(
    @SerializedName("total_count") var totalCount: Int,
    @SerializedName("incomplete_results") var incompleteResults: Boolean,
    @SerializedName("items") var items: List<T>
)