package com.example.movieapp.model.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class HistoryEntity(
    @PrimaryKey(autoGenerate = true) val id: Long,
    val genre: String,
    val title: String,
    val description: String
)
