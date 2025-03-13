package com.comp.lyricsapp.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "projects") // ✅ FIXED: Table name should match DAO queries
data class ProjectDto(
    @PrimaryKey(autoGenerate = true) // ✅ FIXED: Correct `@PrimaryKey` usage
    val id: Long,
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "project_timestamp")
    val timestamp: String
)
