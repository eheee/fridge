package com.heee.fridgetube.data.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.heee.fridgetube.data.ItemWithRecipes
import com.heee.fridgetube.data.RecipeWithItems

@Dao
interface ItemRecipeCrossDao {

    @Transaction
    @Query("SELECT * FROM Item")
    suspend fun getItemWithRecipes(): List<ItemWithRecipes>

    @Transaction
    @Query("SELECT * FROM Item WHERE id IN (:itemIds)")
    suspend fun getItemWithRecipes(itemIds: List<Long>): List<ItemWithRecipes>

    @Transaction
    @Query("SELECT * FROM Recipe")
    suspend fun getRecipeWithItems(): List<RecipeWithItems>

    @Transaction
    @Query("SELECT * FROM Recipe WHERE videoId IN (:videoIds)")
    suspend fun getRecipeWithItems(videoIds: List<String>): List<RecipeWithItems>
}