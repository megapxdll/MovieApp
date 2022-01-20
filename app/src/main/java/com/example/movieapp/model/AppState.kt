package com.example.movieapp.model

import com.example.movieapp.model.entities.Content

sealed class AppState {
    data class Success(val contentData: List<Content>) : AppState()
    data class Error(val error: Throwable) : AppState()
    object Loading : AppState()
}