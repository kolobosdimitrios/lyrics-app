package com.comp.lyricsapp.domain.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "project_table")
data class Project(
    @PrimaryKey(true)
    val id: Long?,
    @ColumnInfo("title")
    val title: String)