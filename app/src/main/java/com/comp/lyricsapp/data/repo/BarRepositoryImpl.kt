package com.comp.lyricsapp.data.repo

import com.comp.lyricsapp.data.local.repo.LocalBarRepository
import com.comp.lyricsapp.data.remote.repo.RemoteBarRepository
import com.comp.lyricsapp.domain.entities.Bar
import com.comp.lyricsapp.domain.entities.BarWithLines
import com.comp.lyricsapp.domain.repositories.BarRepository
import kotlinx.coroutines.flow.Flow

class BarRepositoryImpl(
    private val localBarRepository: LocalBarRepository,
    private val remoteBarRepository: RemoteBarRepository
): BarRepository {

    override suspend fun updateBar(updatedBar: Bar) {
        localBarRepository.updateBar(updatedBar)
    }

    override suspend fun deleteProjectBars(projectId: Long, projectBarIds: List<Long>) {
        localBarRepository.deleteProjectBars(projectId, projectBarIds)
    }

    override suspend fun deleteAllProjectBars(projectId: Long) {
        localBarRepository.deleteAllProjectBars(projectId)
    }

    override suspend fun createBar(bar: Bar) {
        localBarRepository.createBar(bar)
    }

    override fun getBarWithLines(barId: Long): Flow<BarWithLines> {
        return localBarRepository.getBarWithLines(barId)
    }
}