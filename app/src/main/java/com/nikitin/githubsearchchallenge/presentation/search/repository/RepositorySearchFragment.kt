package com.nikitin.githubsearchchallenge.presentation.search.repository

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
import com.nikitin.githubsearchchallenge.presentation.main.MainActivity
import com.nikitin.githubsearchchallenge.presentation.main.MainViewModel
import com.nikitin.githubsearchchallenge.R
import com.nikitin.githubsearchchallenge.databinding.FragmentRepositorySearchBinding
import com.nikitin.githubsearchchallenge.di.ViewModelFactory
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class RepositorySearchFragment : DaggerFragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private val viewModel by viewModels<RepositorySearchViewModel> { viewModelFactory }
    private val activityViewModel by viewModels<MainViewModel>({ activity as MainActivity }) { viewModelFactory }

    private var _binding: FragmentRepositorySearchBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRepositorySearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val repositorySearchAdapter = RepositorySearchAdapter()
        binding.searchResults.apply {
            adapter = repositorySearchAdapter
            val horizontalDecoration = DividerItemDecoration(context, DividerItemDecoration.VERTICAL).apply {
                setDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.horizontal_devider_drawable)!!)
            }
            addItemDecoration(horizontalDecoration)
        }

        viewModel.repositoryItems.observe(viewLifecycleOwner) {
            repositorySearchAdapter.submitList(it)
        }

        activityViewModel.searchQuery.observe(viewLifecycleOwner) {
            if (it.isNullOrEmpty()) return@observe
            viewModel.search(it)
        }

        viewModel.error.observe(viewLifecycleOwner) {
            if (it == null) return@observe
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
            viewModel.setErrorShown()
        }

        viewModel.isLoading.observe(viewLifecycleOwner) {
            binding.progressBar.isVisible = it
        }

        viewModel.totalSearchResultsAmount.observe(viewLifecycleOwner) {
            binding.searchResultsAmount.text =
                resources.getString(R.string.search_repositories_match, it)
        }

        binding.searchResults.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val lastVisibleItemPosition =
                    (recyclerView.layoutManager as LinearLayoutManager).findLastVisibleItemPosition()
                viewModel.onScrolled(lastVisibleItemPosition)
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}