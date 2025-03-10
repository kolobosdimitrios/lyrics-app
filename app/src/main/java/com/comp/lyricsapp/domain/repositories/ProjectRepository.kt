package com.comp.lyricsapp.domain.repositories

import com.comp.lyricsapp.data.model.relations.ProjectWithBarsRelationEntity
import com.comp.lyricsapp.domain.entities.Project
import com.comp.lyricsapp.domain.entities.ProjectWithBars
import kotlinx.coroutines.flow.Flow

interface ProjectRepository {

    fun getAll() : Flow<List<Project>>

    fun getProjectWithBars(id: Long) : Flow<ProjectWithBars>

    suspend fun update(updatedProject: Project)

    suspend fun deleteAll()

    suspend fun delete(project: Project)

    suspend fun create(project: Project)
}