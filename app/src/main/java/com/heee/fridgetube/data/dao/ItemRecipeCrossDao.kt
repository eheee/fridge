package com.heee.fridgetube.data.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.heee.fridgetube.data.Item
import com.heee.fridgetube.data.ItemWithRecipes

@Dao
interface ItemRecipeCrossDao {

    @Transaction
    @Query("SELECT * FROM Item")
    suspend fun getItemWithRecipes(): List<ItemWithRecipes>

    @Transaction
    @Query("SELECT * FROM Item WHERE id IN (:itemIds)")
    suspend fun getItemWithRecipes(itemIds: List<Long>): List<ItemWithRecipes>
}