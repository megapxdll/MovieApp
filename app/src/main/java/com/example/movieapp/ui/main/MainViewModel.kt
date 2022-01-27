package com.example.movieapp.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movieapp.model.AppState
import com.example.movieapp.model.entities.Content
import com.example.movieapp.model.repositories.Repository
import java.lang.Thread.sleep

class MainViewModel(private val repository: Repository) : ViewModel() {
    private val liveData = MutableLiveData<AppState>()

    fun getLiveData(): LiveData<AppState> = liveData


    private fun getDataFromLocalSource(contentType : List<Content>) {
        liveData.value = AppState.Loading
        Thread {
            sleep(1000)
            liveData.postValue(
                    AppState.Success(contentType)
            )
        }.start()
    }
}