package com.axion.news.network.api

import androidx.lifecycle.LiveData
import com.axion.news.network.responses.Content
import com.axion.news.network.responses.ContentResponse
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.QueryMap

interface NetworkApi {
    @Headers("Content-Type: application/json")
    @GET("v2/content/posts")
    fun getContents(@QueryMap map: HashMap<String, String>): LiveData<ApiResponse<ContentResponse>>
}