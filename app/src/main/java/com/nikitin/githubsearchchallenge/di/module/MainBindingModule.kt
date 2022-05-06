package com.nikitin.githubsearchchallenge.di.module

import androidx.lifecycle.ViewModel
import com.nikitin.core.di.keys.ViewModelKey
import com.nikitin.githubsearchchallenge.di.scope.ActivityScope
import com.nikitin.githubsearchchallenge.main_activity.MainActivity
import com.nikitin.githubsearchchallenge.main_activity.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
abstract class MainBindingModule {
    @ContributesAndroidInjector
    @ActivityScope
    abstract fun mainActivity(): MainActivity

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindMainViewModel(mainViewModel: MainViewModel): ViewModel
}