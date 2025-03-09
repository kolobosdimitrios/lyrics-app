package com.comp.lyricsapp.domain.repositories

import com.comp.lyricsapp.domain.entities.Bar
import com.comp.lyricsapp.domain.entities.Line
import kotlinx.coroutines.flow.Flow

interface LineRepository {

    suspend fun updateLine(newLine: Line)

    suspend fun deleteBarLines(barId: Long, barLines: List<Line>)

    suspend fun createLine(newLine: Line)
}