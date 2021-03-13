package com.heee.fridgetube.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.heee.fridgetube.data.Library

@Dao
interface LibraryDao {
    @Insert
    suspend fun addLibrary(vararg library: Library)

    @Query("SELECT * FROM Library ORDER BY date ASC")
    suspend fun getLibrary(): List<Library>
}