package com.heee.fridgetube.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Library(
    @PrimaryKey
    val videoId: String,
    val date: Long = System.currentTimeMillis()
)