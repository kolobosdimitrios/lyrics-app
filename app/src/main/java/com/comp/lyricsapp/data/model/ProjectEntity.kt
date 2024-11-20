package com.comp.lyricsapp.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "project_table")
data class ProjectEntity(
    @PrimaryKey(true)
    val id: Long?,
    @ColumnInfo("title")
    val title: String)