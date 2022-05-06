package com.nikitin.ui_details.di.module

import androidx.lifecycle.ViewModel
import com.nikitin.core.di.keys.ViewModelKey
import com.nikitin.ui_details.DetailsFeatureFragment
import com.nikitin.ui_details.DetailsFeatureViewModel
import com.nikitin.ui_details.di.scope.DetailsFeatureScope
import com.nikitin.ui_details.repository.DetailsRepositoryFragment
import com.nikitin.ui_details.repository.DetailsRepositoryViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
abstract class DetailsFeatureModule {

    @DetailsFeatureScope
    @ContributesAndroidInjector
    abstract fun detailsFeatureFragment(): DetailsFeatureFragment

    @Binds
    @IntoMap
    @ViewModelKey(DetailsFeatureViewModel::class)
    abstract fun detailsFeatureViewModel(detailsFeatureViewModel: DetailsFeatureViewModel): ViewModel

    @DetailsFeatureScope
    @ContributesAndroidInjector
    abstract fun detailsRepositoryFragment(): DetailsRepositoryFragment

    @Binds
    @IntoMap
    @ViewModelKey(DetailsRepositoryViewModel::class)
    abstract fun detailsRepositoryViewModel(detailsRepositoryViewModel: DetailsRepositoryViewModel): ViewModel
}