package com.nikitin.githubsearchchallenge.feature.repositorysearch

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nikitin.githubsearchchallenge.data.model.GitHubRepositoryModel
import com.nikitin.githubsearchchallenge.data.model.GitHubSearchResponseModel
import com.nikitin.githubsearchchallenge.domain.SearchInteractor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject

class RepositorySearchViewModel @Inject constructor(private val repositoryInteractor: SearchInteractor) :
    ViewModel() {

    private val _repositoryItems =
        MutableLiveData<Response<GitHubSearchResponseModel<GitHubRepositoryModel>>>()
    var repositoryItems: LiveData<Response<GitHubSearchResponseModel<GitHubRepositoryModel>>> = _repositoryItems

    fun performCallTEST() {
        viewModelScope.launch {
            val result = withContext(Dispatchers.IO) {
                    repositoryInteractor.searchRepository("weather")
            }
            _repositoryItems.postValue(result)
        }
    }
}