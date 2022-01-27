package com.example.movieapp.ui.itemPage

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movieapp.model.AppState
import com.example.movieapp.model.repositories.Repository

class ItemPageViewModel(private val repository: Repository) : ViewModel() {
    private val localLiveData: MutableLiveData<AppState> = MutableLiveData()
    val dataLiveData: LiveData<AppState>
        get() {
        return localLiveData
    }

    fun loadData() {
        localLiveData.value = AppState.Loading
        Thread {
            val data = repository.getGenresFromServer()
            localLiveData.postValue(AppState.Success(listOf(data)))
        }.start()
    }
}