package com.comp.lyricsapp.data.local.repo

import com.comp.lyricsapp.data.local.dao.BarDao
import com.comp.lyricsapp.data.mapper.toDomainEntity
import com.comp.lyricsapp.data.mapper.toDto
import com.comp.lyricsapp.domain.entities.Bar
import com.comp.lyricsapp.domain.entities.BarWithLines
import com.comp.lyricsapp.domain.repositories.BarRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class LocalBarRepository(private val barDao: BarDao) : BarRepository {


    override suspend fun updateBar(updatedBar: Bar) {
        barDao.updateBar(updatedBar.toDto())
    }

    override suspend fun deleteProjectBars(projectId: Long, projectBarIds: List<Long>) {
        barDao.deleteProjectBars(
            projectId = projectId,
                barIds = projectBarIds
        )
    }

    override suspend fun deleteAllProjectBars(projectId: Long) {
        barDao.deleteAllProjectBars(projectId)
    }

    override suspend fun createBar(bar: Bar) {
        barDao.insertBar(bar.toDto())
    }

    override fun getBarWithLines(barId: Long): Flow<BarWithLines> {
        return barDao.getBarWithLines(barId).map { it.toDomainEntity() }
    }
}