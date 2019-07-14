package com.axion.news.di

import dagger.Module

@Suppress("unused")
@Module(includes = [ViewModelModule::class, DBProviderModule::class])
class NewsAppModule {
}