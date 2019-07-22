package com.axion.news.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.axion.news.network.responses.Content

@Dao
interface ContentDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(content: Content)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(contents: List<Content>)

    @Query("SELECT * FROM contents ORDER BY updatedAt ASC")
    fun loadAll(): LiveData<List<Content>>
}