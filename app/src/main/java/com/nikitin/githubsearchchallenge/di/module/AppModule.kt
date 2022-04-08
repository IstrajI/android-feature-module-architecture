package com.nikitin.githubsearchchallenge.di.module

import com.google.gson.GsonBuilder
import com.nikitin.githubsearchchallenge.BuildConfig
import com.nikitin.githubsearchchallenge.repositories.GitHubAPI
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class AppModule {
    @Provides
    @Singleton
    fun provideGsonConverterFactory(): GsonConverterFactory {
        return GsonBuilder()
            .setLenient()
            .create().let {
                GsonConverterFactory.create(it)
            }
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder().apply {
            val logging = HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }
            addInterceptor(logging)
            connectTimeout(BuildConfig.GITHUB_CONNECT_TIMEOUT, TimeUnit.SECONDS)
            readTimeout(BuildConfig.GITHUB_READ_TIMEOUT, TimeUnit.SECONDS)
            writeTimeout(BuildConfig.GITHUB_WRITE_TIMEOUT, TimeUnit.SECONDS)
        }.build()
    }

    @Provides
    @Singleton
    fun githubApi(
        okHttpClient: OkHttpClient,
        gsonConverterFactory: GsonConverterFactory
    ): GitHubAPI {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.GITHUB_URL)
            .addConverterFactory(gsonConverterFactory)
            .client(okHttpClient)
            .build()
            .create(GitHubAPI::class.java)
    }
}