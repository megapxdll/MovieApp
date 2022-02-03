package com.example.movieapp.ui.itemPage

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.model.AppState
import com.example.movieapp.model.repositories.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ItemPageViewModel(private val repository: Repository) : ViewModel() {
    private val localLiveData: MutableLiveData<AppState> = MutableLiveData()
    val dataLiveData: LiveData<AppState>
        get() {
        return localLiveData
    }

    fun loadData() = with(viewModelScope) {
        localLiveData.value = AppState.Loading

        launch(Dispatchers.IO) {
            val data = repository.getGenresFromServer()
            withContext(Dispatchers.Main) { localLiveData.value = AppState.Success(listOf(data)) }
        }
    }
}