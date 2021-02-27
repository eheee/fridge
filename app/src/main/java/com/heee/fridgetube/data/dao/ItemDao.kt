package com.heee.fridgetube.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.heee.fridgetube.data.Item

@Dao
interface ItemDao {
    @Insert
    suspend fun addItem(item: Item)

    @Query("select * from Item order by id asc")
    suspend fun fetchAllItems(): List<Item>
}