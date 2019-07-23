package com.axion.news.di

import android.app.Application
import androidx.room.Room
import com.axion.news.database.AxionDb
import com.axion.news.database.dao.AuthorDao
import com.axion.news.database.dao.ContentDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Suppress("unused")
@Module
class DBProviderModule {

    @Singleton
    @Provides
    fun provideDb(app: Application): AxionDb = Room.databaseBuilder(app, AxionDb::class.java, "axion.db").fallbackToDestructiveMigration().build()

    @Singleton
    @Provides
    fun provideContentDao(db: AxionDb): ContentDao = db.contentDao()

    @Singleton
    @Provides
    fun provideAuthor(db: AxionDb): AuthorDao = db.authorDao()
}