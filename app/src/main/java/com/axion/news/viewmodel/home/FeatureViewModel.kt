package com.axion.news.viewmodel.home

import com.axion.news.repository.ContentRepository
import com.axion.news.viewmodel.factory.ObservableViewModel
import javax.inject.Inject

class FeatureViewModel @Inject constructor(
    private val contentRepo: ContentRepository
): ObservableViewModel() {

    fun getAllContent() = contentRepo.getAllContent()
}