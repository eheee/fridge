package com.heee.fridgetube.ui.memo

import android.app.Application
import androidx.lifecycle.*
import com.heee.fridgetube.data.Memo
import com.heee.fridgetube.data.room.AppDatabase
import kotlinx.coroutines.launch

class MemoViewModel(application: Application) : AndroidViewModel(application) {
    private val context = application.applicationContext
    private val db = AppDatabase.getAppDatabase(context)

    private val _memo = MutableLiveData<List<Memo>>()
    val memo: LiveData<List<Memo>>
        get() = _memo

    fun getMemos() {
        val memoDao = db.memoDao()
        viewModelScope.launch {
            _memo.value = memoDao.getMemos()
        }
    }

    fun addMemo(memo: Memo){
        val memoDao = db.memoDao()
        viewModelScope.launch {
            memoDao.addMemo(memo)
        }
    }

}