package com.nikitin.githubsearchchallenge.di.module

import com.nikitin.githubsearchchallenge.presentation.search.repository.RepositorySearchFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBindingModule {
    @ContributesAndroidInjector
    abstract fun repositorySearchFragment(): RepositorySearchFragment
}