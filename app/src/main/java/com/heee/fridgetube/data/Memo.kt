package com.heee.fridgetube.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Memo(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val inputTime: Long = System.currentTimeMillis(),
    val comment: String
)
