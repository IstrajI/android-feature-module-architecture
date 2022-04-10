package com.nikitin.githubsearchchallenge.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nikitin.githubsearchchallenge.MainViewModel
import com.nikitin.githubsearchchallenge.di.ViewModelFactory
import com.nikitin.githubsearchchallenge.di.ViewModelKey
import com.nikitin.githubsearchchallenge.feature.repositorysearch.RepositorySearchViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelBindingModule {
    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindMainViewModel(mainViewModel: MainViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(RepositorySearchViewModel::class)
    abstract fun bindRepositorySearchViewModel(repositorySearchViewModel: RepositorySearchViewModel): ViewModel
}