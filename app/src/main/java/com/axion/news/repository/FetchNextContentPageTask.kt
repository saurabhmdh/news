package com.axion.news.repository

import com.axion.news.database.AxionDb
import com.axion.news.network.api.NetworkApi
import timber.log.Timber

class FetchNextContentPageTask constructor(
    private val api: NetworkApi,
    private val db: AxionDb
) : Runnable {
    override fun run() {
        Timber.i("I am running well")
    }
}