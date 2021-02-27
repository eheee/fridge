package com.heee.fridgetube.ui.fridge

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import com.heee.fridgetube.data.room.AppDatabase
import kotlinx.coroutines.launch

class FridgeViewModel(application: Application) : AndroidViewModel(application) {
    var db: AppDatabase
    val context = application.applicationContext
    init {
        db = Room.databaseBuilder(context, AppDatabase::class.java, "fridge")
            .createFromAsset("fridge")
            .build()
        viewModelScope.launch {
            val itemDao = db.itemDao()
            val list = itemDao.fetchAllItems()
            Log.e(TAG, list.toString())
            Toast.makeText(context, list.toString(), Toast.LENGTH_SHORT).show()
        }
    }

    companion object {
        const val TAG = "FridgeViewModel"
    }
}