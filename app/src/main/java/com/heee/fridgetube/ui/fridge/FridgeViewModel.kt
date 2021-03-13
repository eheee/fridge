package com.heee.fridgetube.ui.fridge

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.heee.fridgetube.data.entity.Cabinet
import com.heee.fridgetube.data.CabinetAndItem
import com.heee.fridgetube.data.room.AppDatabase
import kotlinx.coroutines.launch

class FridgeViewModel(application: Application) : AndroidViewModel(application) {
    val context = application.applicationContext
    var db: AppDatabase = AppDatabase.getAppDatabase(context)

    private val _cabinet = MutableLiveData<List<CabinetAndItem>>()
    val cabinet: LiveData<List<CabinetAndItem>>
        get() = _cabinet

    init {
        fetchCabinetAndItem()
    }

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
            _cabinet.value = cabinetAndItem
        }
    }

    companion object {
        const val TAG = "FridgeViewModel"
    }
}