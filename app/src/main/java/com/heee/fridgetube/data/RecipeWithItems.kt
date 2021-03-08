package com.heee.fridgetube.data

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

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
