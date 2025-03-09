package com.comp.lyricsapp.data.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey


@Entity(
    tableName = "bars",
    foreignKeys = [
        ForeignKey(
            entity = ProjectDto::class,
            parentColumns = ["id"],
            childColumns = ["projectId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class BarDto(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val projectId: Long
)