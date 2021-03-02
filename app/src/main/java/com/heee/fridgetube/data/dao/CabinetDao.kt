package com.heee.fridgetube.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.heee.fridgetube.data.Cabinet

@Dao
interface CabinetDao {
    @Query("SELECT * FROM Cabinet")
    fun fetchAllCabinets(): List<Cabinet>

    @Insert
    suspend fun addCabinet(cabinet: Cabinet)
}