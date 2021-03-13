package com.heee.fridgetube.ui.library

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.heee.fridgetube.data.Library
import com.heee.fridgetube.data.Recipe
import com.heee.fridgetube.data.RecipeCard
import com.heee.fridgetube.data.room.AppDatabase
import kotlinx.coroutines.launch
import java.util.*

class LibraryViewModel(application: Application) : AndroidViewModel(application) {
    private val context = application.applicationContext

    private val _recipes = MutableLiveData<List<RecipeCard>>()
    val recipes: LiveData<List<RecipeCard>>
        get() = _recipes

    private val db = AppDatabase.getAppDatabase(context)

    fun addLibrary(library: Library) {
        val libraryDao = db.libraryDao()
        viewModelScope.launch {
            libraryDao.addLibrary(library)
        }
        getLibrary()
    }

    //TODO when should be invoked?
    fun getLibrary() {
        val cabinetAndItemDao = db.cabinetAndItemDao()
        val libraryDao = db.libraryDao()
        val itemRecipeCrossDao = db.itemRecipeCrossDao()
        viewModelScope.launch {
            val videoIds = libraryDao.getLibrary().map { it.videoId }
            Log.d(TAG, videoIds.toString())

            //items in Fridge
            val cabinetAndItems = cabinetAndItemDao.getCabinetAndItem()
            val itemsInFridge = mutableListOf<Long>()
            for (cabinetAndItem in cabinetAndItems) {
                itemsInFridge.add(cabinetAndItem.item.id)
            }

            // 보유한 재료 비율이 높은 순으로 레시피 정렬
            val recipeCards = mutableListOf<RecipeCard>()
            val recipeWithItems = itemRecipeCrossDao.getRecipeWithItems(videoIds.toList())

            for(recipeWithItem in recipeWithItems) {
                val recipeCard = RecipeCard(recipeWithItem.recipe, itemsInFridge)
                for (item in recipeWithItem.items){
                    if(itemsInFridge.contains(item.id)){
                        recipeCard.inFridge.add(item)
                    }else{
                        recipeCard.notInFridge.add(item)
                    }
                }

                recipeCards.add(recipeCard)
            }

            _recipes.value = recipeCards

        }
    }

    companion object {
        const val TAG = "LibraryViewModel"
    }
}