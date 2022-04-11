package com.nikitin.githubsearchchallenge.feature.repositorysearch

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nikitin.githubsearchchallenge.data.model.Outcome
import com.nikitin.githubsearchchallenge.domain.SearchInteractor
import com.nikitin.githubsearchchallenge.feature.repositorysearch.RepositorySearchViewModel.Constants.FIRST_PAGE
import com.nikitin.githubsearchchallenge.feature.repositorysearch.entity.GitHubSearchResponseToSearchResultUIModel
import com.nikitin.githubsearchchallenge.feature.repositorysearch.entity.RepositoryUIModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class RepositorySearchViewModel @Inject constructor(
    private val repositoryInteractor: SearchInteractor,
    private val gitHubSearchResponseToSearchResultUIModel: GitHubSearchResponseToSearchResultUIModel
) :
    ViewModel() {
    private val _repositoryItems =
        MutableLiveData<List<RepositoryUIModel>>()
    var repositoryItems: LiveData<List<RepositoryUIModel>> = _repositoryItems

    private val _totalSearchResultsAmount =
        MutableLiveData<String>()
    var totalSearchResultsAmount: LiveData<String> = _totalSearchResultsAmount

    private val _isLoading =
        MutableLiveData<Boolean>()
    var isLoading: LiveData<Boolean> = _isLoading

    private val _error =
        MutableLiveData<String?>()
    var error: LiveData<String?> = _error

    private var lastSearchName = ""
    private var lastSearchPage = FIRST_PAGE

    fun search(phrase: String) = viewModelScope.launch(Dispatchers.IO) {
        if (_isLoading.value == true) return@launch
        _isLoading.postValue(true)

        val searchResults = repositoryInteractor.searchRepository(
            name = phrase,
            page = FIRST_PAGE
        )
        when (searchResults) {
            is Outcome.SuccessOutcome -> {
                val data =
                    gitHubSearchResponseToSearchResultUIModel.map(searchResults.data)
                lastSearchPage = FIRST_PAGE
                lastSearchName = phrase
                _repositoryItems.postValue(data.result)
                _totalSearchResultsAmount.postValue(data.totalCount)
            }
            is Outcome.ErrorOutcome -> {
                _error.postValue(searchResults.errorMessage)
            }
        }
        _isLoading.postValue(false)
    }

    fun searchNext() = viewModelScope.launch(Dispatchers.IO) {
        if (_isLoading.value == true) return@launch
        _isLoading.postValue(true)

        val searchResults = repositoryInteractor.searchRepository(
            name = lastSearchName,
            page = lastSearchPage + 1
        )
        when (searchResults) {
            is Outcome.SuccessOutcome -> {
                val data =
                    gitHubSearchResponseToSearchResultUIModel.map(searchResults.data)
                _repositoryItems.postValue(
                    (_repositoryItems.value ?: listOf()) + data.result
                )
                lastSearchPage++
            }
            is Outcome.ErrorOutcome -> {
                _error.postValue(searchResults.errorMessage)
            }
        }

        _isLoading.postValue(false)
    }

    fun onScrolled(lastVisibleItemPosition: Int) {
        val items = repositoryItems.value ?: return
        if (lastVisibleItemPosition > items.size - 1 - Constants.THRESHOLD) searchNext()
    }

    fun setErrorShown() {
        _error.value = null
    }

    object Constants {
        const val THRESHOLD = 20
        const val FIRST_PAGE = 1
    }
}