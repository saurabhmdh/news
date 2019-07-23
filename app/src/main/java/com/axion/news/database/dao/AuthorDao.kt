package com.axion.news.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.axion.news.database.table.Author


@Dao
interface AuthorDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(author: Author)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(contents: List<Author>)

    @Query("SELECT * FROM author ORDER BY id ASC")
    fun loadAll(): LiveData<List<Author>>
}