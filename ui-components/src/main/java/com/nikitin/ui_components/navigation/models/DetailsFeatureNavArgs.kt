package com.nikitin.ui_components.navigation.models

import android.os.Bundle

class DetailsFeatureNavArgs(val repositoryUrl: String) {
    companion object {
        const val URL_ARG = "repository_url"
        fun fromBundle(bundle: Bundle): DetailsFeatureNavArgs {
            val repositoryUrl = bundle.getString(URL_ARG, "")
            return DetailsFeatureNavArgs(repositoryUrl)
        }
    }
}