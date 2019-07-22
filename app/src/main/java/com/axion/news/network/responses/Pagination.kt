package com.axion.news.network.responses

import com.google.gson.annotations.SerializedName


data class Pagination (
    @SerializedName("next") var next: Int = 0,
    @SerializedName("total") var total: Int = 0,
    @SerializedName("pages") var pages: Int = 0,
    @SerializedName("prev") var prev: String? = null,
    @SerializedName("limit") var limit: Int = 0,
    @SerializedName("page") var page: Int = 0
)