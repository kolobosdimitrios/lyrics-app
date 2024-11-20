package com.comp.lyricsapp.data.repo

import com.comp.lyricsapp.data.model.ProjectEntity
import kotlinx.coroutines.flow.Flow

interface ProjectRepository {

    fun getAll() : Flow<List<ProjectEntity>>

    suspend fun get(id: Long) : ProjectEntity

    suspend fun deleteAll()

    suspend fun delete(projectEntity: ProjectEntity)

    suspend fun create(projectEntity: ProjectEntity)
}