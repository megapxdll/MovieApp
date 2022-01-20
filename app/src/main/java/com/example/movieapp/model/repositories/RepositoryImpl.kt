package com.example.movieapp.model.repositories

import com.example.movieapp.model.entities.Content
import com.example.movieapp.model.entities.getWishContent

class RepositoryImpl : Repository {
    override fun getWishContentFromLocalStorage() = getWishContent()
}