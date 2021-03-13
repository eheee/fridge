package com.heee.fridgetube.ui.home

import android.app.Application
import androidx.lifecycle.*
import com.heee.fridgetube.data.CounterTop
import com.heee.fridgetube.data.room.AppDatabase
import kotlinx.coroutines.launch
import java.util.*

class HomeViewModel(application: Application) : AndroidViewModel(application) {
    private val context = application.applicationContext

    private val _recipes = MutableLiveData<Set<CounterTop>>()
    val recipes: LiveData<Set<CounterTop>>
        get() = _recipes

    private val db: AppDatabase = AppDatabase.getAppDatabase(context)

    fun getRecipes() {
        val cabinetAndItemDao = db.cabinetAndItemDao()
        val itemRecipeCrossDao = db.itemRecipeCrossDao()
        viewModelScope.launch {
            //
            val cabinetAndItems = cabinetAndItemDao.getCabinetAndItem()
            val fridge = mutableListOf<Long>()
            for (cabinetAndItem in cabinetAndItems) {
//                if(cabinetAndItem.item.isSpecial){
                    fridge.add(cabinetAndItem.item.id)
//                }
            }

            // 냉장고에 있는 재료로 만들 수 있는 레시피
            val itemWithRecipes = itemRecipeCrossDao.getItemWithRecipes(fridge)
            val videos = itemWithRecipes.flatMap { it.recipes }
            val videoIds = videos.map { it.videoId }.toSet()

            // 보유한 재료 비율이 높은 순으로 레시피 정렬
            val sortedRecipe: TreeSet<CounterTop> = sortedSetOf(CounterTop)  //Use companion object as Comparator
            val recipeWithItems = itemRecipeCrossDao.getRecipeWithItems(videoIds.toList())

            for(recipeWithItem in recipeWithItems) {
                sortedRecipe.add(CounterTop(recipeWithItem.recipe, recipeWithItem.items, fridge))
            }

            _recipes.value = sortedRecipe
        }
    }
}