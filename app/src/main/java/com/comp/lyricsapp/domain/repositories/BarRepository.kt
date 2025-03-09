package com.comp.lyricsapp.domain.repositories

import com.comp.lyricsapp.domain.entities.Bar
import kotlinx.coroutines.flow.Flow

interface BarRepository {


    suspend fun updateBar(updatedBar: Bar)

    suspend fun deleteProjectBars(projectId: Long, projectBars: List<Bar>)

    suspend fun deleteBar(bar: Bar)

    suspend fun createBar(bar: Bar)
}