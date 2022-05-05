package com.nikitin.ui_search.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nikitin.domain.search.SearchInteractor
import com.nikitin.ui_search.mapper.GitHubSearchResponseToSearchResultUIModel
import com.nikitin.ui_search.repository.model.RepositoryUIModel
import com.nikitin.ui_search.repository.SearchRepositoryViewModel.Constants.FIRST_PAGE
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class SearchRepositoryViewModel @Inject constructor(
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
        if (_isLoading.value == true || phrase == lastSearchName) return@launch
        _isLoading.postValue(true)

        val searchResults = repositoryInteractor.searchRepository(
            name = phrase,
            page = FIRST_PAGE
        )
        searchResults.fold( onSuccess = {
            val data =
                gitHubSearchResponseToSearchResultUIModel.mapRepositories(it)
            lastSearchPage = FIRST_PAGE
            lastSearchName = phrase
            _repositoryItems.postValue(data.result)
            _totalSearchResultsAmount.postValue(data.totalCount)
        }, onFailure = {
            _error.postValue(it.message)
        })
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
            searchResults.fold(onSuccess = {
                val data =
                    gitHubSearchResponseToSearchResultUIModel.mapRepositories(it)
                _repositoryItems.postValue(
                    (_repositoryItems.value ?: listOf()) + data.result
                )
                lastSearchPage++
            }, onFailure = {
                _error.postValue(it.message)
            })

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