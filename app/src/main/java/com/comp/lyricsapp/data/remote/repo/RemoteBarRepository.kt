package com.comp.lyricsapp.data.remote.repo

import com.comp.lyricsapp.data.remote.api.BarApi
import com.comp.lyricsapp.domain.entities.Bar
import com.comp.lyricsapp.domain.entities.BarWithLines
import com.comp.lyricsapp.domain.repositories.BarRepository
import kotlinx.coroutines.flow.Flow

class RemoteBarRepository(private val barApi: BarApi): BarRepository {

    override suspend fun updateBar(updatedBar: Bar) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteProjectBars(projectId: Long, projectBarIds: List<Long>) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteAllProjectBars(projectId: Long) {
        TODO("Not yet implemented")
    }

    override suspend fun createBar(bar: Bar) {
        TODO("Not yet implemented")
    }

    override fun getBarWithLines(barId: Long): Flow<BarWithLines> {
        TODO("Not yet implemented")
    }
}