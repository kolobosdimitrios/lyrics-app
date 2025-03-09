package com.comp.lyricsapp.data.local.repo

import com.comp.lyricsapp.data.local.dao.LineDao
import com.comp.lyricsapp.data.mapper.toDto
import com.comp.lyricsapp.domain.entities.Line
import com.comp.lyricsapp.domain.repositories.LineRepository

class LocalLineRepository(private val lineDao: LineDao): LineRepository {

    override suspend fun insertLine(newLine: Line) {
        lineDao.insertLine(newLine.toDto())
    }


    override suspend fun updateLine(updatedLine: Line) {
        lineDao.updateLine(updatedLine.toDto())
    }

    override suspend fun deleteBarLines(barId: Long, barLines: List<Long>) {
        lineDao.deleteBarLines(
            lines = barLines,
            barId = barId
        )
    }


    override suspend fun createLine(newLine: Line) {
        lineDao.insertLine(newLine.toDto())
    }
}