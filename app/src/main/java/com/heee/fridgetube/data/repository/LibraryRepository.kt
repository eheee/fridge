package com.heee.fridgetube.data.repository

import android.content.Context
import android.database.sqlite.SQLiteConstraintException
import android.util.Log
import com.heee.fridgetube.data.CounterTop
import com.heee.fridgetube.data.entity.Library

class LibraryRepository(context: Context) : BaseRepository(context) {

    suspend fun addLibrary(library: Library) {
        try {
            val libraryDao = db.libraryDao()
            libraryDao.insert(library)
        } catch (e: SQLiteConstraintException) {
            Log.e(TAG, "UNIQUE constraint failed (videoId:${library.videoId})")
        }
    }

    suspend fun fetchLibrary(): List<CounterTop> {
        val libraryDao = db.libraryDao()
        val cabinetAndItemDao = db.cabinetAndItemDao()
        val itemRecipeCrossDao = db.itemRecipeCrossDao()

        val libraryVideoIds = libraryDao.getLibraries().map { it.videoId }.toList()
        // TODO Fetch fridge data from ViewModel.
        val fridge = cabinetAndItemDao.getCabinetAndItem().map { it.item.id }

        val counterTopList = mutableListOf<CounterTop>()
        val recipeWithItems = itemRecipeCrossDao.getRecipeWithItems(libraryVideoIds)

        for (recipeWithItem in recipeWithItems) {
            counterTopList.add(CounterTop(recipeWithItem.recipe, recipeWithItem.items, fridge))
        }

        return counterTopList
    }

    companion object {
        const val TAG = "LibraryRepository"
    }

}