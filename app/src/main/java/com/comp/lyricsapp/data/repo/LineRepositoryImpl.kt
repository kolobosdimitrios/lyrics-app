package com.comp.lyricsapp.data.repo

import com.comp.lyricsapp.data.local.repo.LocalLineRepository
import com.comp.lyricsapp.data.remote.repo.RemoteLineRepository
import com.comp.lyricsapp.domain.entities.Line
import com.comp.lyricsapp.domain.repositories.LineRepository
import kotlinx.coroutines.flow.Flow

class LineRepositoryImpl(
    private val localLineRepository: LocalLineRepository,
    private val remoteLineRepository: RemoteLineRepository
): LineRepository {

    override suspend fun insertLine(newLine: Line) {
        localLineRepository.insertLine(newLine)
    }


    override suspend fun updateLine(updatedLine: Line) {
        localLineRepository.updateLine(updatedLine)
    }

    override suspend fun deleteBarLines(barId: Long, barLines: List<Long>) {
        localLineRepository.deleteBarLines(barId, barLines)
    }

    override suspend fun createLine(newLine: Line) {
        localLineRepository.createLine(newLine)
    }
}