package com.axion.news.network.responses

import com.google.gson.annotations.SerializedName

data class ContentResponse (
    @SerializedName("meta") var meta: Meta,
    @SerializedName("posts") var posts: List<Content>
)