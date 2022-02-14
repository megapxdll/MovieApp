package com.example.movieapp.model.entities

import com.example.movieapp.model.entities.serverEntities.GenresDTO
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface GenresApi {
    @GET("genres")
    fun getContent(
        @Query("id") lat: Double,
        @Query("name") lon: Double
    ) : Call<GenresDTO>
}