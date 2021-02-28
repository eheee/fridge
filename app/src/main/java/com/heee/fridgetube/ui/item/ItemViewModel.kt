package com.heee.fridgetube.ui.item

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.*
import androidx.room.Room
import com.heee.fridgetube.data.Item
import com.heee.fridgetube.data.room.AppDatabase
import kotlinx.coroutines.launch

class ItemViewModel(application: Application) : AndroidViewModel(application) {
    private var _items = MutableLiveData<List<Item>>()
    val items: LiveData<List<Item>> = _items

    private val context = application.applicationContext

    private val db: AppDatabase = Room.databaseBuilder(context, AppDatabase::class.java, "fridge")
        .createFromAsset("fridge")
        .build()

    init {
        val dao = db.itemDao()

        viewModelScope.launch {
            val items = dao.fetchAllItems()
            _items.value = items
        }
    }
}