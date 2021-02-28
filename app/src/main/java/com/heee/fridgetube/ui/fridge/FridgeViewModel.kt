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
    val context = application.applicationContext
    var db: AppDatabase

    init {
        db = Room.databaseBuilder(context, AppDatabase::class.java, "fridge")
            .build()
    }

    fun addCabinet(id: Long) {
        viewModelScope.launch {

        }
    }

    companion object {
        const val TAG = "FridgeViewModel"
    }
}