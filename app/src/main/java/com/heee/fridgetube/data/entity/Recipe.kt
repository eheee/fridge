package com.heee.fridgetube.data.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity
data class Recipe (
    @PrimaryKey
    val videoId: String,
    val name: String
) : Parcelable
