package com.axion.news.repository

import androidx.lifecycle.LiveData
import com.axion.news.constants.Constants
import com.axion.news.network.api.*
import com.axion.news.network.responses.ContentResponse
import com.axion.news.services.AppExecutors
import com.axion.news.util.network.RateLimiter
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ContentRepository @Inject constructor(
    private val executors: AppExecutors,
    private val services: NetworkApi
) {
    private val repoListRateLimit = RateLimiter<String>(10, TimeUnit.HOURS)

    //Now we need to cache data..
    fun getAllContent(): LiveData<Resource<ContentResponse>> {
        return object : NetworkBoundResource<ContentResponse>(executors) {
            override fun createCall() = services.getContents(hashMapOf(Pair("key", Constants.API_KEY)))
        }.asLiveData()
    }

    fun getCacheContents(): LiveData<Resource<ContentResponse>> {
        return object :CacheNetworkBoundResource<ContentResponse, ContentResponse>(executors) {
            override fun saveCallResult(item: ContentResponse) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun shouldFetch(data: ContentResponse?): Boolean {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun loadFromDb(): LiveData<ContentResponse> {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun createCall(): LiveData<ApiResponse<ContentResponse>> {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

        }.asLiveData()
    }
}