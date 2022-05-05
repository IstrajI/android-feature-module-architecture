package com.nikitin.ui_details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nikitin.ui_components.navigation.models.DetailsFeatureNavArgs
import javax.inject.Inject

class DetailsFeatureViewModel @Inject constructor() : ViewModel() {

    private val _featureArgumentUrl =
        MutableLiveData<String>()
    var featureArgumentUrl: LiveData<String> = _featureArgumentUrl

    fun setFeatureArguments(navArgs: DetailsFeatureNavArgs) {
        _featureArgumentUrl.value = navArgs.repositoryUrl
    }
}