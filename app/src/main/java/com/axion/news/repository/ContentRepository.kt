package com.axion.news.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.axion.news.constants.Constants
import com.axion.news.database.dao.ContentDao
import com.axion.news.network.api.*
import com.axion.news.network.responses.Content
import com.axion.news.network.responses.ContentResponse
import com.axion.news.services.AppExecutors
import com.axion.news.util.network.RateLimiter
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ContentRepository @Inject constructor(
    private val executors: AppExecutors,
    private val services: NetworkApi,
    private val contentDao: ContentDao,
    var application: Application
) {
    private val repoListRateLimit = RateLimiter<String>(10, TimeUnit.HOURS)
    val key = Constants.CONTENTES
    //Now we need to cache data..
    fun getAllContent(): LiveData<Resource<ContentResponse>> {
        return object : NetworkBoundResource<ContentResponse>(executors) {
            override fun createCall() = services.getContents(hashMapOf(Pair("key", Constants.API_KEY)))
        }.asLiveData()
    }

    fun getCacheContents(): LiveData<Resource<List<Content>>> {
        return object: CacheNetworkBoundResource<List<Content>, ContentResponse>(executors) {
            override fun shouldFetch(data: List<Content>?) = data.isNullOrEmpty() || repoListRateLimit.shouldFetch(key)
            override fun saveCallResult(item: ContentResponse) {
                contentDao.insertAll(item.posts)
            }
            override fun loadFromDb() = contentDao.loadAll()
            override fun createCall() = services.getContents(hashMapOf(Pair("key", Constants.API_KEY)/*, Pair("limit", "all")*/))

        }.asLiveData()
    }

    fun getTrendingNews(): LiveData<Resource<List<Content>>> {
        return object: CacheNetworkBoundResource<List<Content>, ContentResponse>(executors) {
            override fun shouldFetch(data: List<Content>?): Boolean {
                return data.isNullOrEmpty() || data.size <= 10 || repoListRateLimit.shouldFetch(key)
            }
            override fun saveCallResult(item: ContentResponse) {
                contentDao.insertAll(item.posts)
            }
            override fun loadFromDb() = contentDao.loadAll()
            override fun createCall() = services.getContents(hashMapOf(Pair("key", Constants.API_KEY), Pair("page", "2")))

        }.asLiveData()
    }

    fun getMagazines(): LiveData<Resource<List<Content>>> {
        return object: CacheNetworkBoundResource<List<Content>, ContentResponse>(executors) {
            override fun shouldFetch(data: List<Content>?): Boolean {
                return data.isNullOrEmpty() || data.size <= 20 || repoListRateLimit.shouldFetch(key)
            }
            override fun saveCallResult(item: ContentResponse) {
                contentDao.insertAll(item.posts)
            }
            override fun loadFromDb() = contentDao.loadAll()
            override fun createCall() = services.getContents(hashMapOf(Pair("key", Constants.API_KEY), Pair("page", "3")))

        }.asLiveData()
    }
}