package com.heee.fridgetube.ui.memo

import android.app.Application
import androidx.lifecycle.*
import com.heee.fridgetube.data.entity.Memo
import com.heee.fridgetube.data.room.AppDatabase
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class MemoViewModel(application: Application) : AndroidViewModel(application) {
    private val context = application.applicationContext
    private val db = AppDatabase.getAppDatabase(context)

    private val _memos = MutableLiveData<Map<String, List<Memo>>>()
    val memos: LiveData<Map<String, List<Memo>>>
        get() = _memos

    fun getMemos() {
        val memoDao = db.memoDao()
        viewModelScope.launch {
            val memos = memoDao.getMemos()
            _memos.value = memos.groupBy { it.inputDate }
        }
    }

    fun addMemo(memo: Memo){
        val memoDao = db.memoDao()
        viewModelScope.launch {
            memoDao.insert(memo)

            //getMemos for sync
            val memos = memoDao.getMemos()
            _memos.value = memos.groupBy { it.inputDate }
        }
    }

    fun deleteMemo(memos: List<Memo>) {
        val memoDao = db.memoDao()
        viewModelScope.launch {
            memoDao.deleteMemosByDate(memos[0].inputDate)
        }
    }
}