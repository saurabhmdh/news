package com.axion.news.network.api

import retrofit2.http.GET
import retrofit2.http.QueryMap

interface NetworkApi {

    @GET("v2/content/posts")
    fun getContents(@QueryMap map: HashMap<String, String>)
}