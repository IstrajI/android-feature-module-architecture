package com.nikitin.ui_details.repository

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.nikitin.core.di.ViewModelFactory
import com.nikitin.extensions.requireGrandParentFragment
import com.nikitin.ui_details.DetailsFeatureViewModel
import com.nikitin.ui_details.databinding.FragmentDetailsRepositoryBinding
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class DetailsRepositoryFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private val viewModel by viewModels<DetailsFeatureViewModel> { viewModelFactory }
    private val featureViewModel by viewModels<DetailsFeatureViewModel>({ requireGrandParentFragment() }) { viewModelFactory }
    private var _binding: FragmentDetailsRepositoryBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailsRepositoryBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        featureViewModel.featureArgumentUrl.observe(viewLifecycleOwner) {
            binding.detailsRepositoryWebView.loadUrl(it)
        }
    }
}