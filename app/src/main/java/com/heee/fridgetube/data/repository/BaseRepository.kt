package com.heee.fridgetube.data.repository

import android.content.Context
import com.heee.fridgetube.data.room.AppDatabase

abstract class BaseRepository(context: Context) {
    val db = AppDatabase.getAppDatabase(context)
}