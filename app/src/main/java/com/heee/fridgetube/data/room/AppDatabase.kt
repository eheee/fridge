package com.heee.fridgetube.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.heee.fridgetube.data.Cabinet
import com.heee.fridgetube.data.Item
import com.heee.fridgetube.data.ItemRecipeCrossRef
import com.heee.fridgetube.data.Recipe
import com.heee.fridgetube.data.dao.CabinetAndItemDao
import com.heee.fridgetube.data.dao.CabinetDao
import com.heee.fridgetube.data.dao.ItemDao
import com.heee.fridgetube.data.dao.ItemRecipeCrossDao

@Database(version = 1, entities = [Item::class, Cabinet::class, Recipe::class, ItemRecipeCrossRef::class])
abstract class AppDatabase : RoomDatabase() {
    abstract fun itemDao(): ItemDao
    abstract fun cabinetDao(): CabinetDao
    abstract fun cabinetAndItemDao(): CabinetAndItemDao
    abstract fun itemRecipeCrossDao(): ItemRecipeCrossDao

    companion object {
        var INSTANCE: AppDatabase? = null

        fun getAppDatabase(context: Context): AppDatabase {
            if (INSTANCE == null) {
                synchronized(this) {    //Lock the companion object.
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        "fridge"
                    ).createFromAsset("fridge").build()
                }
            }
            return INSTANCE!!
        }

        fun destroyAppDatabase() {
            INSTANCE = null
        }

//        val MIGRATION_1_2 = object : Migration(1, 2){
//            override fun migrate(database: SupportSQLiteDatabase) {
//                TODO("Not yet implemented")
//            }
//        }
    }
}