package com.comp.lyricsapp.data.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(

    tableName = "lines",
    foreignKeys = [ForeignKey(
        entity = BarDto::class,
        parentColumns = ["id"],
        childColumns = ["barId"],
        onDelete = ForeignKey.CASCADE
    )],
    indices = [Index("barId")] // Optimizes queries
)
data class LineDto(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val barId: Long,
    val line: String,
    val timestamp: String
) {
}