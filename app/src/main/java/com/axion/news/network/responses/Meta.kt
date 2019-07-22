package com.axion.news.network.responses

import com.google.gson.annotations.SerializedName


data class Meta (@SerializedName("pagination") var pagination: Pagination? = null)