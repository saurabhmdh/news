package com.axion.news.repository

import androidx.lifecycle.LiveData
import com.axion.news.constants.Constants
import com.axion.news.database.dao.AuthorDao
import com.axion.news.database.table.Author
import com.axion.news.network.api.CacheNetworkBoundResource
import com.axion.news.network.api.NetworkApi
import com.axion.news.network.api.Resource
import com.axion.news.network.responses.author.AuthorResponse
import com.axion.news.services.AppExecutors
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepository @Inject constructor(
    private val executors: AppExecutors,
    private val services: NetworkApi,
    private val authorDao: AuthorDao
) {

    fun getAuthors(): LiveData<Resource<List<Author>>> {
        return object : CacheNetworkBoundResource<List<Author>, AuthorResponse>(executors) {
            override fun shouldFetch(data: List<Author>?) = data.isNullOrEmpty()
            override fun saveCallResult(item: AuthorResponse) {
                authorDao.insertAll(item.authors)
            }
            override fun loadFromDb() = authorDao.loadAll()
            override fun createCall() = services.getAuthors(hashMapOf(Pair("key", Constants.API_KEY)))

        }.asLiveData()
    }
}