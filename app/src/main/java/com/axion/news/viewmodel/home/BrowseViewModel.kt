package com.axion.news.viewmodel.home

import com.axion.news.repository.ContentRepository
import com.axion.news.viewmodel.factory.ObservableViewModel
import javax.inject.Inject

class BrowseViewModel @Inject constructor(
    private val contentRepo: ContentRepository
): ObservableViewModel() {

    fun getTrendingNews() = contentRepo.getTrendingNews()
    fun getMagazines() = contentRepo.getMagazines()
}