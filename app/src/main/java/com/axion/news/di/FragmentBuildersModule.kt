package com.axion.news.di

import com.axion.news.views.fragments.author.AuthorFragment
import com.axion.news.views.fragments.detail.DetailFragment
import com.axion.news.views.fragments.home.BrowseFragment
import com.axion.news.views.fragments.home.FeatureFragment
import com.axion.news.views.fragments.home.HomeFragment
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

    @ContributesAndroidInjector
    abstract fun contributeDetailFragment(): DetailFragment

    @ContributesAndroidInjector
    abstract fun contributeAuthorFragment(): AuthorFragment
}