package com.heee.fridgetube.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.heee.fridgetube.data.entity.Memo

@Dao
interface MemoDao: BaseDao<Memo> {

    @Query("SELECT * FROM Memo ORDER BY inputTime DESC")
    suspend fun getMemos(): List<Memo>
}