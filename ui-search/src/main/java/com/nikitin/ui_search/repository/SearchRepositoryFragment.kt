package com.nikitin.ui_search.repository

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nikitin.base.BaseFragment
import com.nikitin.ui_search.databinding.FragmentRepositorySearchBinding
import com.nikitin.core.di.module.ViewModelFactory
import com.nikitin.extensions.requireGrandParentFragment
import com.nikitin.ui_search.R
import com.nikitin.ui_search.SearchFeatureFragment
import com.nikitin.ui_search.SearchFeatureViewModel
import com.nikitin.ui_search.databinding.FragmentSearchFeatureBinding
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class SearchRepositoryFragment : BaseFragment<FragmentRepositorySearchBinding>() {
    private val viewModel by viewModels<SearchRepositoryViewModel> { viewModelFactory }
    private val featureViewModel by viewModels<SearchFeatureViewModel>({ requireGrandParentFragment() }) { viewModelFactory }
    override val bindingInflater =
        { layoutInflater: LayoutInflater, viewGroup: ViewGroup?, attachToParent: Boolean ->
            FragmentRepositorySearchBinding.inflate(
                layoutInflater,
                viewGroup,
                attachToParent
            )
        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initList()
        initSearch()
        initStates()
    }

    private fun initList() {
        val searchRepositoryAdapter = SearchRepositoryAdapter { url: String ->
            (requireGrandParentFragment() as SearchFeatureFragment).searchItemClicked(url)
        }
        binding.repositoryList.apply {
            adapter = searchRepositoryAdapter
            val horizontalDecoration =
                DividerItemDecoration(context, DividerItemDecoration.VERTICAL).apply {
                    setDrawable(
                        ContextCompat.getDrawable(
                            requireContext(),
                            R.drawable.horizontal_devider_drawable
                        )!!
                    )
                }
            addItemDecoration(horizontalDecoration)
        }

        binding.repositoryList.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val lastVisibleItemPosition =
                    (recyclerView.layoutManager as LinearLayoutManager).findLastVisibleItemPosition()
                viewModel.onScrolled(lastVisibleItemPosition)
            }
        })

        viewModel.repositoryItems.observe(viewLifecycleOwner) {
            searchRepositoryAdapter.submitList(it)
        }
    }

    private fun initSearch() {
        viewModel.totalSearchResultsAmount.observe(viewLifecycleOwner) {
            binding.searchResultsAmount.text =
                resources.getString(R.string.search_repositories_match, it)
        }
        featureViewModel.searchQuery.observe(viewLifecycleOwner) {
            if (it.isNullOrEmpty()) return@observe
            viewModel.search(it)
        }
    }

    private fun initStates() {
        viewModel.error.observe(viewLifecycleOwner) {
            if (it == null) return@observe
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
            viewModel.setErrorShown()
        }

        viewModel.isLoading.observe(viewLifecycleOwner) {
            binding.progressBar.isVisible = it
        }
    }
}