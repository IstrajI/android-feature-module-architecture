package com.nikitin.ui_details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.nikitin.core.di.module.ViewModelFactory
import com.nikitin.ui_components.DeepLinkNavigator
import com.nikitin.ui_components.navigation.models.DetailsFeatureNavArgs
import com.nikitin.ui_details.databinding.FragmentDetailsFeatureBinding
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class DetailsFeatureFragment: DaggerFragment() {
    @Inject
    lateinit var navigator: DeepLinkNavigator
    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private val viewModel by viewModels<DetailsFeatureViewModel> { viewModelFactory }
    private var _binding: FragmentDetailsFeatureBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailsFeatureBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            val featureArgs = DetailsFeatureNavArgs.fromBundle(it)
            viewModel.setFeatureArguments(featureArgs)
        }
    }
}