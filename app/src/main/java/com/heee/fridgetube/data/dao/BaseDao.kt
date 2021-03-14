package com.heee.fridgetube.data.dao

import androidx.room.Delete
import androidx.room.Insert

interface BaseDao<T> {
    @Insert
    suspend fun insert(vararg obj: T)

    @Delete
    suspend fun update(vararg obj: T)

    @Delete
    suspend fun delete(vararg obj: T)
}