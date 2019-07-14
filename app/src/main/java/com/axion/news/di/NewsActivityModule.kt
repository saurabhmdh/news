package com.axion.news.di

import com.readystatesoftware.chuck.internal.ui.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Suppress("unused")
@Module
abstract class NewsActivityModule {

    @ContributesAndroidInjector(modules = [FragmentBuildersModule::class])
    abstract fun contributeHomeActivity(): MainActivity
}