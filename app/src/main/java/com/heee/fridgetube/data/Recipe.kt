package com.heee.fridgetube.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Recipe (
    @PrimaryKey
    val videoId: String,
    val name: String
)
