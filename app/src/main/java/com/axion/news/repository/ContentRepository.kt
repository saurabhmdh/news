package com.axion.news.repository

import androidx.lifecycle.LiveData
import com.axion.news.constants.Constants
import com.axion.news.network.api.*
import com.axion.news.network.responses.ContentResponse
import com.axion.news.services.AppExecutors
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ContentRepository @Inject constructor(
    private val executors: AppExecutors,
    private val services: NetworkApi
) {

    fun getAllContent(): LiveData<Resource<ContentResponse>> {
        return object : NetworkBoundResource<ContentResponse>(executors) {
            override fun createCall() = services.getContents(hashMapOf(Pair("key", Constants.API_KEY)))
        }.asLiveData()
    }
}