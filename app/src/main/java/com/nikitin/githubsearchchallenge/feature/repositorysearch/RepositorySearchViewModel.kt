package com.nikitin.githubsearchchallenge.feature.repositorysearch

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nikitin.githubsearchchallenge.domain.SearchInteractor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class RepositorySearchViewModel @Inject constructor(private val repositoryInteractor: SearchInteractor) :
    ViewModel() {


    fun performCallTEST() {
        viewModelScope.launch(Dispatchers.IO) {
            val results = repositoryInteractor.searchRepository("weather")
            Log.d("TestPish", "search results : ${results}")
        }
    }
}