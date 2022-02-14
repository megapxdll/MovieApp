package com.example.movieapp.model.database

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

interface History {
    @Query("SELECT * FROM HistoryEntity")
    fun all(): List<HistoryEntity>

    @Query("SELECT * FROM HistoryEntity WHERE genre LIKE :genre")
    fun getDataByWord(genre: String): List<HistoryEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(entity: HistoryEntity)

    @Update
    fun update(entity: HistoryEntity)

    @Delete
    fun delete(entity: HistoryEntity)

    @Query("DELETE FROM HistoryEntity WHERE genre = :genre")
    fun deleteByCityName(genre: String)
}