package com.heee.fridgetube.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.heee.fridgetube.data.Item
import com.heee.fridgetube.data.dao.ItemDao

@Database(version = 1, entities = [Item::class])
abstract class AppDatabase : RoomDatabase() {
    abstract fun itemDao(): ItemDao
}