package com.nikitin.githubsearchchallenge.di.module

import com.nikitin.githubsearchchallenge.presentation.main.MainActivity
import com.nikitin.githubsearchchallenge.di.scope.ActivityScope
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBindingModule {
    @ContributesAndroidInjector
    @ActivityScope
    abstract fun mainActivity(): MainActivity
}