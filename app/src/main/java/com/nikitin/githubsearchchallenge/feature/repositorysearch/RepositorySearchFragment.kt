package com.nikitin.githubsearchchallenge.feature.repositorysearch

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.nikitin.githubsearchchallenge.R
import com.nikitin.githubsearchchallenge.di.ViewModelFactory
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class RepositorySearchFragment: DaggerFragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private val viewModel by viewModels<RepositorySearchViewModel> { viewModelFactory }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_repository_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.performCallTEST()
    }
}