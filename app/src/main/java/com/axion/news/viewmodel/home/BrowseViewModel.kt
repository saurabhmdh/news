package com.axion.news.viewmodel.home

import android.app.Application
import androidx.lifecycle.LiveData
import com.axion.news.R
import com.axion.news.repository.ContentRepository
import com.axion.news.util.livedata.MakeLiveData
import com.axion.news.viewmodel.factory.ObservableViewModel
import com.axion.news.vo.Genres
import javax.inject.Inject

class BrowseViewModel @Inject constructor(
    private val contentRepo: ContentRepository,
    val application: Application
): ObservableViewModel() {

    fun getTrendingNews() = contentRepo.getTrendingNews()
    fun getMagazines() = contentRepo.getMagazines()

    fun getGenres(): LiveData<List<Genres>> {
        val listData = mutableListOf<Genres>()
        for (item in 0 until getGenresSize() step 1) {
            val color = application.resources.getIntArray(R.array.array_category_colors)[item]

            listData.add(Genres(application.resources.getStringArray(R.array.array_section_display_name)[item], color))
        }
        return MakeLiveData.create(listData)
    }

    private fun getGenresSize() = application.resources.getStringArray(R.array.array_section_display_name).size
}