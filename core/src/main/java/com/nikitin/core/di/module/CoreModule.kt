package com.nikitin.core.di.module

import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module

@Module
abstract class CoreModule {
    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}