package com.comp.lyricsapp.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "lines",
    foreignKeys = [ForeignKey(
        entity = BarDto::class,
        parentColumns = ["id"],
        childColumns = ["bar_id"],
        onDelete = ForeignKey.CASCADE
    )],
    indices = [Index("bar_id")]
)
data class LineDto(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    @ColumnInfo(name = "bar_id") // ✅ FIXED: Correct `@ColumnInfo` syntax
    val barId: Long,
    val line: String,
    @ColumnInfo(name = "line_timestamp") // ✅ FIXED: Correct `@ColumnInfo` syntax
    val timestamp: String
)
