package com.nikitin.ui_details.repository

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.nikitin.base.BaseFragment
import com.nikitin.extensions.requireGrandParentFragment
import com.nikitin.ui_details.DetailsFeatureViewModel
import com.nikitin.ui_details.databinding.FragmentDetailsRepositoryBinding

class DetailsRepositoryFragment : BaseFragment<FragmentDetailsRepositoryBinding>() {
    private val viewModel by viewModels<DetailsFeatureViewModel> { viewModelFactory }
    private val featureViewModel by viewModels<DetailsFeatureViewModel>({ requireGrandParentFragment() }) { viewModelFactory }
    override val bindingInflater =
        { layoutInflater: LayoutInflater, viewGroup: ViewGroup?, attachToParent: Boolean ->
            FragmentDetailsRepositoryBinding.inflate(
                layoutInflater,
                viewGroup,
                attachToParent
            )
        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        featureViewModel.featureArgumentUrl.observe(viewLifecycleOwner) {
            binding.detailsRepositoryWebView.loadUrl(it)
        }
    }
}