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
import com.nikitin.githubsearchchallenge.R
import com.nikitin.githubsearchchallenge.databinding.FragmentRepositorySearchBinding
import com.nikitin.githubsearchchallenge.di.ViewModelFactory
import com.nikitin.githubsearchchallenge.presentation.main.MainActivity
import com.nikitin.githubsearchchallenge.presentation.main.MainViewModel
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

        initList()
        initSearch()
        initStates()
    }

    private fun initList() {
        val repositorySearchAdapter = RepositorySearchAdapter()
        binding.repositoryList.apply {
            adapter = repositorySearchAdapter
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
            repositorySearchAdapter.submitList(it)
        }
    }

    private fun initSearch() {
        viewModel.totalSearchResultsAmount.observe(viewLifecycleOwner) {
            binding.searchResultsAmount.text =
                resources.getString(R.string.search_repositories_match, it)
        }
        activityViewModel.searchQuery.observe(viewLifecycleOwner) {
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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}