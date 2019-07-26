package com.axion.news.util.livedata

import androidx.lifecycle.LiveData

class MakeLiveData<T : Any?> private constructor(var data: T) : LiveData<T>(){
    init {
        // use post instead of set since this can be created on any thread
        postValue(data)
    }
    companion object {
        fun <T> create(data: T): LiveData<T> {
            return MakeLiveData(data)
        }
    }
}