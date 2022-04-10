package com.nikitin.githubsearchchallenge.feature.repositorysearch

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nikitin.githubsearchchallenge.data.model.Outcome
import com.nikitin.githubsearchchallenge.domain.SearchInteractor
import com.nikitin.githubsearchchallenge.feature.repositorysearch.entity.GitHubSearchResponseToSearchResultUIModel
import com.nikitin.githubsearchchallenge.feature.repositorysearch.entity.RepositoryUIModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RepositorySearchViewModel @Inject constructor(private val repositoryInteractor: SearchInteractor) :
    ViewModel() {
    private val _repositoryItems =
        MutableLiveData<List<RepositoryUIModel>>()
    var repositoryItems: LiveData<List<RepositoryUIModel>> = _repositoryItems

    private var lastSearchName = "weather"
    private var lastSearchPage = 1
    private var canLoadMore = true

    fun searchNext() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val searchResults = repositoryInteractor.searchRepositoryNextPage(
                    name = lastSearchName,
                    page = lastSearchPage
                )
                when (searchResults) {
                    is Outcome.SuccessOutcome -> {
                        val data = GitHubSearchResponseToSearchResultUIModel().map(searchResults.data)
                        _repositoryItems.postValue(
                            (_repositoryItems.value ?: listOf()) + data.result
                        )
                        lastSearchPage++
                    }
                    is Outcome.ErrorOutcome -> {
                        Log.d("TestPish", "error ${searchResults.errorMessage}")
                    }
                }
            }
        }
    }

    fun onScrolled(lastVisibleItemPosition: Int) {
        val items = repositoryItems.value ?: return
        if (lastVisibleItemPosition > items.size - 1 - Constants.THRESHOLD
            && canLoadMore
        ) {
            searchNext()
        }
    }

    fun search(query: String) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val searchResults = repositoryInteractor.searchRepositoryNextPage(
                    name = query,
                    page = 1
                )
                when (searchResults) {
                    is Outcome.SuccessOutcome -> {
                        val data = GitHubSearchResponseToSearchResultUIModel().map(searchResults.data)
                        _repositoryItems.postValue(
                            (data.result))
                        //lastSearchPage++
                    }
                    is Outcome.ErrorOutcome -> {
                        Log.d("TestPish", "error ${searchResults.errorMessage}")
                    }
                }
            }
        }
    }

    object Constants {
        const val THRESHOLD = 4
    }
}