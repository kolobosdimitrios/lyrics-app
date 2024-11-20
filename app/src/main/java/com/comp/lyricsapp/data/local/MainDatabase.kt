package com.comp.lyricsapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.comp.lyricsapp.data.model.ProjectEntity

@Database(
    entities = [
        ProjectEntity::class
    ],
    version = 2,
    exportSchema = false
)
abstract class MainDatabase :RoomDatabase() {

    abstract fun projectDAO() : ProjectDAO
}