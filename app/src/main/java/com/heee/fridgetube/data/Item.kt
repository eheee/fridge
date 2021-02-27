package com.heee.fridgetube.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Item (
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val name: String,
    val category: Int = 0,
    val isSpecial: Boolean = false
) {
    companion object {
        const val ETC = 0
        const val MEAT = 1
        const val VEGETABLE = 2
        const val SEASONING = 3
    }
}