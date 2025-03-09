package com.comp.lyricsapp.data.local.repo

import com.comp.lyricsapp.data.local.dao.LineDao
import com.comp.lyricsapp.domain.entities.Line
import com.comp.lyricsapp.domain.repositories.LineRepository
import kotlinx.coroutines.flow.Flow

class LocalLineRepository(private val lineDao: LineDao): LineRepository {


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