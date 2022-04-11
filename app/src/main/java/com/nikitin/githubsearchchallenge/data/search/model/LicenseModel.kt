package com.nikitin.githubsearchchallenge.data.search.model

import com.google.gson.annotations.SerializedName

data class LicenseModel(
    @SerializedName("key") var key: String,
    @SerializedName("name") var name: String,
    @SerializedName("url") var url: String,
    @SerializedName("spdx_id") var spdxId: String,
    @SerializedName("node_id") var nodeId: String,
    @SerializedName("html_url") var htmlUrl: String
)