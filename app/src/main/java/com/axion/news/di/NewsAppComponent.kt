package com.axion.news.di

import android.app.Application
import com.axion.news.NewsApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton


@SuppressWarnings("unchecked")
@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        NewsAppModule::class,
        NewsActivityModule::class
    ]
)
interface NewsAppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder
        fun build(): NewsAppComponent
    }

    fun inject(app: NewsApplication)
}