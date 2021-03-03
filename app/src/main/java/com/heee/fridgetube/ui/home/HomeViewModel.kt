package com.heee.fridgetube.ui.home

import android.app.Application
import androidx.lifecycle.*
import com.heee.fridgetube.data.Recipe
import com.heee.fridgetube.data.dao.ItemRecipeCrossDao
import com.heee.fridgetube.data.room.AppDatabase
import kotlinx.coroutines.launch

class HomeViewModel(application: Application) : AndroidViewModel(application) {
    private val context = application.applicationContext

    private val _recipes = MutableLiveData<List<Recipe>>()
    val recipes: LiveData<List<Recipe>>
        get() = _recipes

    val db: AppDatabase = AppDatabase.getAppDatabase(context)

    fun getRecipes() {
        val itemRecipeCrossDao = db.itemRecipeCrossDao()
        viewModelScope.launch {
            val itemWithRecipes = itemRecipeCrossDao.getItemWithRecipes()
            _recipes.value = itemWithRecipes[0].recipes
        }
    }
}