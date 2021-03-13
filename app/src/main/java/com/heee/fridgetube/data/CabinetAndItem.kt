package com.heee.fridgetube.data

import androidx.room.Embedded
import androidx.room.Relation
import com.heee.fridgetube.data.entity.Cabinet
import com.heee.fridgetube.data.entity.Item


data class CabinetAndItem (
    @Embedded val cabinet: Cabinet,
    @Relation(
        parentColumn = "itemId",
        entityColumn = "id"
    )
    val item: Item
)
