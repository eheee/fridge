package com.heee.fridgetube.data.dao

import androidx.room.Dao
import androidx.room.Query
import com.heee.fridgetube.data.entity.Library

@Dao
interface LibraryDao : BaseDao<Library> {

    @Query("SELECT * FROM Library ORDER BY date ASC")
    suspend fun getLibraries(): List<Library>

    @Query("DELETE FROM Library WHERE videoId = :videoId")
    suspend fun deleteByVideoId(videoId: String)
}