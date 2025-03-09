package com.comp.lyricsapp.data.repo

import com.comp.lyricsapp.domain.entities.Line
import com.comp.lyricsapp.domain.repositories.LineRepository
import kotlinx.coroutines.flow.Flow

class LineRepositoryImpl: LineRepository {


    override suspend fun updateLine(newLine: Line) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteBarLines(barId: Long, barLines: List<Line>) {
        TODO("Not yet implemented")
    }

    override suspend fun createLine(newLine: Line) {
        TODO("Not yet implemented")
    }
}