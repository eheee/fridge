package com.heee.fridgetube.ui.library

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.heee.fridgetube.data.entity.Library
import com.heee.fridgetube.data.CounterTop
import com.heee.fridgetube.data.room.AppDatabase
import kotlinx.coroutines.launch

class LibraryViewModel(application: Application) : AndroidViewModel(application) {
    private val context = application.applicationContext

    private val _counterTopList = MutableLiveData<List<CounterTop>>()
    val recipes: LiveData<List<CounterTop>>
        get() = _counterTopList

    private val db = AppDatabase.getAppDatabase(context)

    fun addLibrary(library: Library) {
        val libraryDao = db.libraryDao()
        viewModelScope.launch {
            libraryDao.insert(library)
        }
        getLibrary()
    }

    //TODO when should be invoked?
    fun getLibrary() {
        val cabinetAndItemDao = db.cabinetAndItemDao()
        val libraryDao = db.libraryDao()
        val itemRecipeCrossDao = db.itemRecipeCrossDao()
        viewModelScope.launch {
            val videoIds = libraryDao.getLibraries().map { it.videoId }
            Log.d(TAG, videoIds.toString())

            // TODO Fetch fridge data from ViewModel.
            val fridge = cabinetAndItemDao.getCabinetAndItem().map { it.item.id }

            // 보유한 재료 비율이 높은 순으로 레시피 정렬
            val counterTopList = mutableListOf<CounterTop>()
            val recipeWithItems = itemRecipeCrossDao.getRecipeWithItems(videoIds.toList())

            for (recipeWithItem in recipeWithItems) {
                counterTopList.add(CounterTop(recipeWithItem.recipe, recipeWithItem.items, fridge))
            }

            _counterTopList.value = counterTopList

        }
    }

    companion object {
        const val TAG = "LibraryViewModel"
    }
}