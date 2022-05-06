package com.nikitin.ui_details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.nikitin.base.BaseFeatureFragment
import com.nikitin.ui_components.navigation.models.DetailsFeatureNavArgs
import com.nikitin.ui_details.databinding.FragmentDetailsFeatureBinding

class DetailsFeatureFragment: BaseFeatureFragment<FragmentDetailsFeatureBinding>() {
    private val viewModel by viewModels<DetailsFeatureViewModel> { viewModelFactory }
    override val bindingInflater =
        { layoutInflater: LayoutInflater, viewGroup: ViewGroup?, attachToParent: Boolean ->
            FragmentDetailsFeatureBinding.inflate(
                layoutInflater,
                viewGroup,
                attachToParent
            )
        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            val featureArgs = DetailsFeatureNavArgs.fromBundle(it)
            viewModel.setFeatureArguments(featureArgs)
        }
    }
}