package com.nikitin.githubsearchchallenge.feature.repositorysearch

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.nikitin.githubsearchchallenge.databinding.FragmentRepositorySearchBinding
import com.nikitin.githubsearchchallenge.di.ViewModelFactory
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class RepositorySearchFragment: DaggerFragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private val viewModel by viewModels<RepositorySearchViewModel> { viewModelFactory }

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
        binding.searchResults.adapter = repositorySearchAdapter

        viewModel.performCallTEST()
        viewModel.repositoryItems.observe(viewLifecycleOwner) {
            val uiList = it.body()?.items?.map {
                RepositorySearchUIModel(id = it.id?: 0,
                    name = it.fullName?: "",
                    description = it.description?: "",
                    stars = it.stargazersCount?: 0,
                    updated = it.updatedAt?: "",
                    language = it.language?: "",
                    licenseName = it.license?.name?: "",
                    url = it.url?: "")
            }
            repositorySearchAdapter.submitList(uiList)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}