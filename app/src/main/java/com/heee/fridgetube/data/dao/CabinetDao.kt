package com.heee.fridgetube.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.heee.fridgetube.data.entity.Cabinet

@Dao
interface CabinetDao : BaseDao<Cabinet> {
    @Query("SELECT * FROM Cabinet")
    fun getCabinets(): List<Cabinet>
}