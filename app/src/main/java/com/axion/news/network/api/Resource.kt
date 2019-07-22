package com.axion.news.network.api

data class Resource<out T> (val status: Status, val data: T?, val message: String?) {
    companion object {
        @JvmStatic
        fun <T> success(data: T?): Resource<T> {
            return Resource(Status.SUCCESS, data, null)
        }
        @JvmStatic
        fun <T> error(msg: String, data: T?): Resource<T> {
            return Resource(Status.ERROR, data, msg)
        }
        @JvmStatic
        fun <T> loading(data: T?): Resource<T> {
            return Resource(Status.LOADING, data, null)
        }

    }

    fun isDataAvailable(): Boolean {
        return data != null
    }
}