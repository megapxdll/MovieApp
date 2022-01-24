package com.example.movieapp.model.repositories

import com.example.movieapp.model.entities.Content

interface Repository {
    fun getFilmsContentFromLocalStorage(): List<Content>

    fun getSeriesContentFromLocalStorage(): List<Content>

    fun getWishListContentFromLocalStorage(): List<Content>

}