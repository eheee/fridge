package com.heee.fridgetube.ui.fridge

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.heee.fridgetube.data.Cabinet
import com.heee.fridgetube.data.room.AppDatabase
import kotlinx.coroutines.launch

class FridgeViewModel(application: Application) : AndroidViewModel(application) {
    val context = application.applicationContext
    var db: AppDatabase = AppDatabase.getAppDatabase(context)

    fun addCabinet(itemId: Long) {
        val dao = db.cabinetDao()
        viewModelScope.launch {
            val cabinet = Cabinet(inputDate = System.currentTimeMillis(), itemId = itemId)
            dao.addCabinet(cabinet)
        }
    }

    fun fetchCabinetAndItem() {
        val dao = db.cabinetAndItemDao()
        viewModelScope.launch {
            val cabinetAndItem = dao.getCabinetAndItem()
            Toast.makeText(context, cabinetAndItem.toString(), Toast.LENGTH_SHORT).show()
        }
    }

    companion object {
        const val TAG = "FridgeViewModel"
    }
}