package com.example.movieapp.model.database

import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.movieapp.App

@androidx.room.Database(
    entities = [
        HistoryEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class Database : RoomDatabase() {
    abstract fun historyDao(): History

    companion object {
        private const val DB_NAME = "add_database.db"

        val db: Database by lazy {
            Room.databaseBuilder(
                App.appInstance,
                Database::class.java,
                DB_NAME
            ).build()
        }
    }
}