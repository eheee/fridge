package com.heee.fridgetube.data

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    foreignKeys = [ForeignKey(
        entity = Item::class,
        parentColumns = ["id"],
        childColumns = ["itemId"]
    )]
)
data class Cabinet(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val inputDate: Long,
    val itemId: Long
)
