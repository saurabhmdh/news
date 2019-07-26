package com.axion.news.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.axion.news.viewmodel.author.AuthorViewModel
import com.axion.news.viewmodel.detail.DetailViewModel
import com.axion.news.viewmodel.home.BrowseViewModel
import com.axion.news.viewmodel.home.FeatureViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Suppress("unused")
@Module
abstract class ViewModelModule {
    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(FeatureViewModel::class)
    abstract fun bindFeatureViewModel(featureViewModel: FeatureViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(DetailViewModel::class)
    abstract fun bindDetailViewModel(detailViewModel: DetailViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(AuthorViewModel::class)
    abstract fun bindAuthorViewModel(detailViewModel: AuthorViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(BrowseViewModel::class)
    abstract fun bindBrowseViewModel(detailViewModel: BrowseViewModel): ViewModel
}