package com.nikitin.githubsearchchallenge.feature.repositorysearch

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nikitin.githubsearchchallenge.repositories.GitHubAPI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class RepositorySearchViewModel @Inject constructor() : ViewModel() {

    @Inject
    lateinit var gitHubAPI: GitHubAPI

    fun performCallTEST() {
        viewModelScope.launch(Dispatchers.IO) {
            Log.d("TestPish", "Dispatcher "+this.coroutineContext)
            val userinfo = gitHubAPI.searchRepository("weather")
            Log.d("TestPish", "userinfo $userinfo")
        }
    }

}