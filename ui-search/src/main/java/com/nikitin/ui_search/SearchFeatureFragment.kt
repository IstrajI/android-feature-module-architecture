package com.nikitin.ui_search

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.nikitin.base.BaseFeatureFragment
import com.nikitin.ui_components.navigation.models.DetailsFeatureNavArgs
import com.nikitin.ui_search.databinding.FragmentSearchFeatureBinding

class SearchFeatureFragment : BaseFeatureFragment<FragmentSearchFeatureBinding>() {
    private val viewModel by viewModels<SearchFeatureViewModel> { viewModelFactory }
    override val bindingInflater =
        { layoutInflater: LayoutInflater, viewGroup: ViewGroup?, attachToParent: Boolean ->
            FragmentSearchFeatureBinding.inflate(
                layoutInflater,
                viewGroup,
                attachToParent
            )
        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initSearchView()
    }

    private fun initSearchView() {
        binding.searchView.setOnEditorActionListener(TextView.OnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                binding.searchView.clearFocus()
                val inputMethod =
                    requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                inputMethod.hideSoftInputFromWindow(binding.searchView.windowToken, 0)
                viewModel.setSearchQuery(binding.searchView.text.toString())
                return@OnEditorActionListener true
            }
            false
        })
    }

    fun searchItemClicked(url: String) {
        requireParentFragment().findNavController().navigate(
            navigator.getDetailsNavDeepLinkRequest(
                DetailsFeatureNavArgs(url)
            )
        )
    }
}