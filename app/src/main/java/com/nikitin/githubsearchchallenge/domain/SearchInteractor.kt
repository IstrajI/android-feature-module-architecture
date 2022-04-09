package com.nikitin.githubsearchchallenge.domain

import com.nikitin.githubsearchchallenge.data.repository.search.SearchRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SearchInteractor @Inject constructor(private val searchRepository: SearchRepository) {

    suspend fun searchRepository(name: String) {
        withContext(Dispatchers.IO) {
            searchRepository.searchRepository(name)
        }
    }
}