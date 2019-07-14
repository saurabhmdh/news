package com.axion.news.di

import com.axion.news.views.fragments.BrowseFragment
import com.axion.news.views.fragments.HomeFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Suppress("unused")
@Module
abstract class FragmentBuildersModule {
    @ContributesAndroidInjector
    abstract fun contributeHomeFragment(): HomeFragment

    @ContributesAndroidInjector
    abstract fun contributeBrowseFragment(): BrowseFragment

}