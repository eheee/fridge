package com.heee.fridgetube.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.heee.fridgetube.data.entity.Item

@Dao
interface ItemDao : BaseDao<Item> {
    @Query("select * from Item order by id asc")
    suspend fun getItems(): List<Item>
}