package com.nikitin.ui_components

import androidx.core.net.toUri
import androidx.navigation.NavDeepLinkRequest
import com.nikitin.ui_components.navigation.models.DetailsFeatureNavArgs
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DeepLinkNavigator @Inject constructor() {
    private val FEATURE_DETAILS_DEEP_LINK = "deeplink://com.nikitin.githubsearchchallenge/details_feature_fragment/"
    fun getDetailsNavDeepLinkRequest(navArgs: DetailsFeatureNavArgs): NavDeepLinkRequest {
        val deepLinkString = "$FEATURE_DETAILS_DEEP_LINK?${DetailsFeatureNavArgs.URL_ARG}=${navArgs.repositoryUrl}"
        return NavDeepLinkRequest.Builder
            .fromUri(deepLinkString.toUri())
            .build()
    }
}