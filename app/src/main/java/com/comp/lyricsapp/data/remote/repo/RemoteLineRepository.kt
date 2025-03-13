package com.comp.lyricsapp.data.remote.repo

import com.comp.lyricsapp.data.remote.api.LineApi
import com.comp.lyricsapp.domain.entities.Line
import com.comp.lyricsapp.domain.repositories.LineRepository
import kotlinx.coroutines.flow.Flow

class RemoteLineRepository(private val lineApi: LineApi): LineRepository {

    override suspend fun insertLine(newLine: Line) {
        TODO("Not yet implemented")
    }

    override suspend fun updateLine(updatedLine: Line) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteBarLines(barLines: List<Line>) {
        TODO("Not yet implemented")
    }

    override suspend fun createLine(newLine: Line) {
        TODO("Not yet implemented")
    }
}