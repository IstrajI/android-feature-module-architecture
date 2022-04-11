package com.nikitin.githubsearchchallenge.presentation.search.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nikitin.githubsearchchallenge.data.network.Outcome
import com.nikitin.githubsearchchallenge.domain.search.interactor.SearchInteractor
import com.nikitin.githubsearchchallenge.presentation.search.repository.RepositorySearchViewModel.Constants.FIRST_PAGE
import com.nikitin.githubsearchchallenge.presentation.search.mapper.GitHubSearchResponseToSearchResultUIModel
import com.nikitin.githubsearchchallenge.presentation.search.model.RepositoryUIModel
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
        MutableLiveData<Int>()
    var totalSearchResultsAmount: LiveData<Int> = _totalSearchResultsAmount

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
                    gitHubSearchResponseToSearchResultUIModel.mapRepositories(searchResults.data)
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

    private fun searchNext() {
        if (_isLoading.value == true
            || (_totalSearchResultsAmount.value ?: 0) <= (_repositoryItems.value?.size ?: 0)
        ) return
        _isLoading.postValue(true)

        viewModelScope.launch(Dispatchers.IO) {
            val searchResults = repositoryInteractor.searchRepository(
                name = lastSearchName,
                page = lastSearchPage + 1
            )
            when (searchResults) {
                is Outcome.SuccessOutcome -> {
                    val data =
                        gitHubSearchResponseToSearchResultUIModel.mapRepositories(searchResults.data)
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