package com.comp.lyricsapp.domain.entities

data class Line(
    val id: Long,
    val barId: Long,
    val line: String,
    val timestamp: String
)