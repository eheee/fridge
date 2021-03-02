package com.heee.fridgetube.data

import androidx.room.Embedded
import androidx.room.Relation


data class CabinetAndItem (
    @Embedded val cabinet: Cabinet,
    @Relation(
        parentColumn = "itemId",
        entityColumn = "id"
    )
    val item: Item
)
