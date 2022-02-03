package com.example.movieapp.model.repositories

import com.example.movieapp.model.DataLoader
import com.example.movieapp.model.entities.Content

class RepositoryImpl : Repository {

    override fun getGenresFromServer(): Content {
        val dto = DataLoader.loadData()
        return Content(
            id = dto?.genres?.id ?: 0,
            name = dto?.genres?.name ?: " "
        )
    }
}