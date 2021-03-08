package com.heee.fridgetube.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.heee.fridgetube.data.Memo

@Dao
interface MemoDao {
    @Insert
    suspend fun addMemo(vararg memo: Memo)

    @Query("SELECT * FROM Memo")
    suspend fun getMemos(): List<Memo>
}