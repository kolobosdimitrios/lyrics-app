package com.comp.lyricsapp.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey


@Entity(
    tableName = "bars",
    foreignKeys = [
        ForeignKey(
            entity = ProjectDto::class,
            parentColumns = ["id"],
            childColumns = ["project_id"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index("project_id")]
)
data class BarDto(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    @ColumnInfo(name = "project_id") // ✅ FIXED: Correct `@ColumnInfo` syntax
    val projectId: Long
)
