package com.comp.lyricsapp.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.comp.lyricsapp.data.local.dao.ProjectDAO
import com.comp.lyricsapp.data.model.ProjectDto

@Database(
    entities = [
        ProjectDto::class
    ],
    version = 2,
    exportSchema = false
)
abstract class MainDatabase :RoomDatabase() {

    abstract fun projectDAO() : ProjectDAO
}