package com.comp.lyricsapp.data.repo

import com.comp.lyricsapp.data.local.repo.LocalLineRepository
import com.comp.lyricsapp.data.remote.repo.RemoteLineRepository
import com.comp.lyricsapp.domain.entities.Line
import com.comp.lyricsapp.domain.repositories.LineRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LineRepositoryImpl @Inject constructor(
    private val localLineRepository: LocalLineRepository,
    private val remoteLineRepository: RemoteLineRepository
): LineRepository {

    override suspend fun insertLine(newLine: Line) {
        localLineRepository.insertLine(newLine)
    }


    override suspend fun updateLine(updatedLine: Line) {
        localLineRepository.updateLine(updatedLine)
    }

    override suspend fun deleteBarLines(barLines: List<Line>) {
        localLineRepository.deleteBarLines(barLines)
    }

    override suspend fun createLine(newLine: Line) {
        localLineRepository.createLine(newLine)
    }
}