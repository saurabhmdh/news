package com.axion.news.di

import com.axion.news.views.fragments.*
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Suppress("unused")
@Module
abstract class FragmentBuildersModule {
    @ContributesAndroidInjector
    abstract fun contributeHomeFragment(): HomeFragment

    @ContributesAndroidInjector
    abstract fun contributeBrowseFragment(): BrowseFragment

    @ContributesAndroidInjector
    abstract fun contributeFeatureFragment(): FeatureFragment
}