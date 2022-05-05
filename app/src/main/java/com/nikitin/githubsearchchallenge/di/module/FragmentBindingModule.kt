package com.nikitin.githubsearchchallenge.di.module

import com.nikitin.ui_details.DetailsFeatureFragment
import com.nikitin.ui_details.repository.DetailsRepositoryFragment
import com.nikitin.ui_search.SearchFeatureFragment
import com.nikitin.ui_search.repository.SearchRepositoryFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBindingModule {
    @ContributesAndroidInjector
    abstract fun repositorySearchFragment(): SearchRepositoryFragment

    @ContributesAndroidInjector
    abstract fun searchFeatureFragment(): SearchFeatureFragment

    @ContributesAndroidInjector
    abstract fun detailsFeatureFragment(): DetailsFeatureFragment

    @ContributesAndroidInjector
    abstract fun detailsRepositoryFragment(): DetailsRepositoryFragment
}