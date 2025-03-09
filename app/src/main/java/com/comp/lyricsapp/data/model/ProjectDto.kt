package com.comp.lyricsapp.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "project_table")
data class ProjectDto(
    @PrimaryKey(true)
    val id: Long,
    @ColumnInfo("title")
    val title: String,
    @ColumnInfo("timestamp")
    val timestamp: String
)
