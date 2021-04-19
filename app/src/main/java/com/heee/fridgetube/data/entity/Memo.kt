package com.heee.fridgetube.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.text.SimpleDateFormat
import java.util.*

@Entity
data class Memo(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val inputTime: Long = System.currentTimeMillis(),
    val inputDate: String = SimpleDateFormat("yyyy-MM-dd").format(Date(inputTime)),
    val comment: String
) {
//    var inputDate: String
//
//    init {
//        val formatter = SimpleDateFormat("yyyy-MM-dd")
//        inputDate = formatter.format(Date(inputTime))
//    }
}
