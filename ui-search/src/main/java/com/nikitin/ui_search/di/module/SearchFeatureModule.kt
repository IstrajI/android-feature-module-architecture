package com.nikitin.ui_search.di.module

import androidx.lifecycle.ViewModel
import com.nikitin.core.di.keys.ViewModelKey
import com.nikitin.ui_search.SearchFeatureFragment
import com.nikitin.ui_search.SearchFeatureViewModel
import com.nikitin.ui_search.di.scope.SearchFeatureScope
import com.nikitin.ui_search.repository.SearchRepositoryFragment
import com.nikitin.ui_search.repository.SearchRepositoryViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
abstract class SearchFeatureModule {

    @SearchFeatureScope
    @ContributesAndroidInjector
    abstract fun searchFeatureFragment(): SearchFeatureFragment

    @Binds
    @IntoMap
    @ViewModelKey(SearchFeatureViewModel::class)
    abstract fun searchFeatureViewModel(searchFeatureViewModel: SearchFeatureViewModel): ViewModel

    @SearchFeatureScope
    @ContributesAndroidInjector
    abstract fun searchRepositoryFragment(): SearchRepositoryFragment

    @Binds
    @IntoMap
    @ViewModelKey(SearchRepositoryViewModel::class)
    abstract fun searchRepositoryViewModel(detailsRepositoryViewModel: SearchRepositoryViewModel): ViewModel
}