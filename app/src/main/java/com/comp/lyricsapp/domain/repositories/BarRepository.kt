package com.comp.lyricsapp.domain.repositories

import com.comp.lyricsapp.domain.entities.Bar
import com.comp.lyricsapp.domain.entities.BarWithLines
import kotlinx.coroutines.flow.Flow

interface BarRepository {


    suspend fun updateBar(updatedBar: Bar)

    suspend fun deleteProjectBars(projectId: Long, projectBarIds: List<Long>)

    suspend fun deleteAllProjectBars(projectId: Long)

    suspend fun createBar(bar: Bar)

    fun getBarWithLines(barId: Long): Flow<BarWithLines>
}