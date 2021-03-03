package com.heee.fridgetube.ui.home

import android.app.Application
import androidx.lifecycle.*
import com.heee.fridgetube.data.Item
import com.heee.fridgetube.data.Recipe
import com.heee.fridgetube.data.dao.ItemRecipeCrossDao
import com.heee.fridgetube.data.room.AppDatabase
import kotlinx.coroutines.launch

class HomeViewModel(application: Application) : AndroidViewModel(application) {
    private val context = application.applicationContext

    private val _recipes = MutableLiveData<List<Recipe>>()
    val recipes: LiveData<List<Recipe>>
        get() = _recipes

    private val db: AppDatabase = AppDatabase.getAppDatabase(context)

    fun getRecipes() {
        val cabinetAndItemDao = db.cabinetAndItemDao()
        val itemRecipeCrossDao = db.itemRecipeCrossDao()
        viewModelScope.launch {
            val cabinetAndItems = cabinetAndItemDao.getCabinetAndItem()
            val itemsInFridge = mutableListOf<Long>()
            for (cabinetAndItem in cabinetAndItems) {
                itemsInFridge.add(cabinetAndItem.item.id)
            }

            val itemWithRecipes = itemRecipeCrossDao.getItemWithRecipes(itemsInFridge)
            val recipesToShow = mutableListOf<Recipe>()
            for (itemWithRecipe in itemWithRecipes) {
                val recipes = itemWithRecipe.recipes
                for(recipe in recipes){
                    recipesToShow.add(recipe)
                }
            }
            _recipes.value = recipesToShow
        }
    }
}