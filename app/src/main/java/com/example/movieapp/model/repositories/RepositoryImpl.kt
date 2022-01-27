package com.example.movieapp.model.repositories

import com.example.movieapp.model.entities.Content
import com.example.movieapp.model.entities.getFilmContent
import com.example.movieapp.model.entities.getSeriesContent
import com.example.movieapp.model.entities.getWishListContent

class RepositoryImpl : Repository {
    override fun getFilmsContentFromLocalStorage() = getFilmContent()
    override fun getSeriesContentFromLocalStorage() = getSeriesContent()
    override fun getWishListContentFromLocalStorage() = getWishListContent()
}