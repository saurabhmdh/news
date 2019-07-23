package com.axion.news.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.axion.news.database.converters.AxionDBConverter
import com.axion.news.database.dao.AuthorDao
import com.axion.news.database.dao.ContentDao
import com.axion.news.database.table.Author
import com.axion.news.network.responses.Content

@Database(entities = [
    Content::class,
    Author::class
], version = 1, exportSchema = false)

@TypeConverters(AxionDBConverter::class)
abstract class AxionDb: RoomDatabase() {
    abstract fun contentDao(): ContentDao
    abstract fun authorDao(): AuthorDao
}