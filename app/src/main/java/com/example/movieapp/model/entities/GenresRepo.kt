package com.example.movieapp.model.entities

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object GenresRepo {
    val api: GenresApi by lazy {
        val adapter = Retrofit.Builder()
            .baseUrl(ApiUtils.baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(ApiUtils.getOkHTTPBuilderWithHeaders())
            .build()

        adapter.create(GenresApi::class.java)
    }
}