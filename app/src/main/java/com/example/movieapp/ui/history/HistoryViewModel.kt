package com.example.movieapp.ui.history

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.model.AppState
import com.example.movieapp.model.repositories.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HistoryViewModel(
    private val repository: Repository
) : ViewModel() {
    val historyLiveData: MutableLiveData<AppState> = MutableLiveData()

    fun getAllHistory() {
        historyLiveData.value = AppState.Loading
        viewModelScope.launch(Dispatchers.IO) {
            historyLiveData.postValue(AppState.Success(repository.getAllHistory()))
        }
    }
}