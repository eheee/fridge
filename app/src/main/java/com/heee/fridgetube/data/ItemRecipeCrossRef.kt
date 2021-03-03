package com.heee.fridgetube.data

import androidx.room.Entity

@Entity(primaryKeys = ["id", "videoId"])
data class ItemRecipeCrossRef(
    val id: Long,
    val videoId: String,
)
