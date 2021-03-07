package com.heee.fridgetube.ui.library

import android.app.Application
import androidx.lifecycle.*
import com.heee.fridgetube.data.Recipe
import com.heee.fridgetube.data.room.AppDatabase
import kotlinx.coroutines.launch

class LibraryViewModel(application: Application) : AndroidViewModel(application) {
    private val context = application.applicationContext

    private val _recipes = MutableLiveData<List<Recipe>>()
    val recipes: LiveData<List<Recipe>>
        get() = _recipes

    val db = AppDatabase.getAppDatabase(context)

    fun getLibrary() {
        viewModelScope.launch {

        }
    }
}