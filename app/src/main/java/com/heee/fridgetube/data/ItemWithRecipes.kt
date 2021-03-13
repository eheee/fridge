package com.heee.fridgetube.data

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.heee.fridgetube.data.entity.Item
import com.heee.fridgetube.data.entity.ItemRecipeCrossRef
import com.heee.fridgetube.data.entity.Recipe

data class ItemWithRecipes(
    @Embedded
    val item: Item,

    @Relation(
        parentColumn = "id",
        entityColumn = "videoId",
        associateBy = Junction(ItemRecipeCrossRef::class))
    val recipes: List<Recipe>,
)
