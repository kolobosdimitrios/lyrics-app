package com.comp.lyricsapp.data.local.repo

import com.comp.lyricsapp.data.local.dao.BarDao
import com.comp.lyricsapp.domain.entities.Bar
import com.comp.lyricsapp.domain.repositories.BarRepository
import kotlinx.coroutines.flow.Flow

class LocalBarRepository(private val barDao: BarDao) : BarRepository {


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