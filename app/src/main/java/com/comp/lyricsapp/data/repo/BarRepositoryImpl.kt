package com.comp.lyricsapp.data.repo

import com.comp.lyricsapp.domain.entities.Bar
import com.comp.lyricsapp.domain.repositories.BarRepository
import kotlinx.coroutines.flow.Flow

class BarRepositoryImpl: BarRepository {



    override suspend fun updateBar(updatedBar: Bar) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteProjectBars(projectId: Long, projectBars: List<Bar>) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteBar(bar: Bar) {
        TODO("Not yet implemented")
    }

    override suspend fun createBar(bar: Bar) {
        TODO("Not yet implemented")
    }
}