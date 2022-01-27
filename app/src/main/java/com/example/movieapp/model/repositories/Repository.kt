package com.example.movieapp.model.repositories

import com.example.movieapp.model.entities.Content

interface Repository {

    fun getGenresFromServer(): Content


}