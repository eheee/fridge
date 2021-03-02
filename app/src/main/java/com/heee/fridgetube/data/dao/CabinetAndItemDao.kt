package com.heee.fridgetube.data.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.heee.fridgetube.data.CabinetAndItem

@Dao
interface CabinetAndItemDao {

    @Transaction
    @Query("SELECT * FROM Cabinet")
    suspend fun getCabinetAndItem(): List<CabinetAndItem>
}