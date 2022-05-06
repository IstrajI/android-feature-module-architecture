package com.nikitin.githubsearchchallenge.di.component

import android.app.Application
import com.nikitin.core.di.module.CoreModule
import com.nikitin.datasource.di.module.DatasourceModule
import com.nikitin.githubsearchchallenge.GitHubApplication
import com.nikitin.githubsearchchallenge.di.module.*
import com.nikitin.ui_details.di.module.DetailsFeatureModule
import com.nikitin.ui_search.di.module.SearchFeatureModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [AndroidSupportInjectionModule::class,
        DatasourceModule::class,
        AppModule::class,
        CoreModule::class,
        MainBindingModule::class,
        SearchFeatureModule::class,
        DetailsFeatureModule::class]
)
interface AppComponent : AndroidInjector<GitHubApplication> {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder
        fun build(): AppComponent
    }
}