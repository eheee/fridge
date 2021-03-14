package com.heee.fridgetube.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.heee.fridgetube.data.entity.Library

@Dao
interface LibraryDao: BaseDao<Library> {

    // TODO QLiteConstraintException: UNIQUE constraint failed: Library.videoId

    @Query("SELECT * FROM Library ORDER BY date ASC")
    suspend fun getLibraries(): List<Library>
}