package com.heee.fridgetube.ui.item

import android.app.Application
import androidx.lifecycle.*
import com.heee.fridgetube.data.entity.Item
import com.heee.fridgetube.data.room.AppDatabase
import kotlinx.coroutines.launch

class ItemViewModel(application: Application) : AndroidViewModel(application) {
    private var _items = MutableLiveData<List<Item>>()
    val items: LiveData<List<Item>> = _items

    private val context = application.applicationContext

    private val db: AppDatabase = AppDatabase.getAppDatabase(context)

    init {
        val dao = db.itemDao()

        viewModelScope.launch {
            val items = dao.getItems()
            _items.value = items
        }
    }
}