package com.heee.fridgetube.ui.library

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.heee.fridgetube.data.entity.Library
import com.heee.fridgetube.data.CounterTop
import com.heee.fridgetube.data.repository.LibraryRepository
import com.heee.fridgetube.data.room.AppDatabase
import kotlinx.coroutines.launch

class LibraryViewModel(application: Application) : AndroidViewModel(application) {
    private val libraryRepository = LibraryRepository(application.applicationContext)

    private val _counterTopList = MutableLiveData<List<CounterTop>>()
    val recipes: LiveData<List<CounterTop>>
        get() = _counterTopList

    private val _isInLibrary = MutableLiveData<Boolean>()
    val isInLibrary: LiveData<Boolean>
        get() = _isInLibrary

    fun addLibrary(library: Library) {
        viewModelScope.launch {
            libraryRepository.addLibrary(library)
        }
    }

    fun deleteLibrary(videoId: String) {
        viewModelScope.launch {
            libraryRepository.deleteLibrary(videoId)
        }
    }


    fun fetchLibrary() {
        viewModelScope.launch {
            _counterTopList.value = libraryRepository.fetchLibrary()
        }
    }


    fun checkIfInLibrary(videoId: String) {
        viewModelScope.launch {
            _isInLibrary.value = libraryRepository.isInLibarry(videoId)
        }
    }

    companion object {
        const val TAG = "LibraryViewModel"
    }
}