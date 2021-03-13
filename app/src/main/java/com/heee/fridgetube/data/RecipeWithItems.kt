package com.heee.fridgetube.data

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.heee.fridgetube.data.entity.Item
import com.heee.fridgetube.data.entity.ItemRecipeCrossRef
import com.heee.fridgetube.data.entity.Recipe

data class RecipeWithItems(
    @Embedded
    val recipe: Recipe,

    @Relation(
        parentColumn = "videoId",
        entityColumn = "id",
        associateBy = Junction(ItemRecipeCrossRef::class)
    )
    val items: List<Item>
)
