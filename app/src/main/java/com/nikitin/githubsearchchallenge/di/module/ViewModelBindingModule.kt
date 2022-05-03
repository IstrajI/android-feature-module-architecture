package com.nikitin.githubsearchchallenge.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nikitin.core.di.ViewModelFactory
import com.nikitin.core.di.ViewModelKey
import com.nikitin.githubsearchchallenge.main.MainViewModel
import com.nikitin.ui_search.SearchFeatureViewModel
import com.nikitin.ui_search.repository.SearchRepositoryViewModel
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
    @ViewModelKey(SearchRepositoryViewModel::class)
    abstract fun bindRepositorySearchViewModel(searchRepositoryViewModel: SearchRepositoryViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SearchFeatureViewModel::class)
    abstract fun bindSearchFeatureViewModel(searchFeatureViewModel: SearchFeatureViewModel): ViewModel
}