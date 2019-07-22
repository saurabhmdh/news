package com.axion.news.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
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
    abstract fun bindShowMoreFilterViewModel(featureViewModel: FeatureViewModel): ViewModel

}