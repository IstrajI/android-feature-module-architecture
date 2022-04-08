package com.nikitin.githubsearchchallenge.di.component

import android.app.Application
import com.nikitin.githubsearchchallenge.GitHubApplication
import com.nikitin.githubsearchchallenge.di.module.ActivityBindingModule
import com.nikitin.githubsearchchallenge.di.module.AppModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidSupportInjectionModule::class, AppModule::class, ActivityBindingModule::class])
interface AppComponent : AndroidInjector<GitHubApplication> {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder
        fun build(): AppComponent
    }
}