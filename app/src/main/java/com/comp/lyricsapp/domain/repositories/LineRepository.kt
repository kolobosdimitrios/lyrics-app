package com.comp.lyricsapp.domain.repositories

import com.comp.lyricsapp.domain.entities.Line

interface LineRepository {

    suspend fun insertLine(newLine: Line)

    suspend fun updateLine(updatedLine: Line)

    suspend fun deleteBarLines(barLines: List<Line>)

    suspend fun createLine(newLine: Line)
}