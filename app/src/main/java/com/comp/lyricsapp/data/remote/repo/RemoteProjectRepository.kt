package com.comp.lyricsapp.data.remote.repo

import com.comp.lyricsapp.data.model.relations.ProjectWithBarsRelationEntity
import com.comp.lyricsapp.data.remote.api.ProjectApi
import com.comp.lyricsapp.domain.entities.Project
import com.comp.lyricsapp.domain.entities.ProjectWithBars
import com.comp.lyricsapp.domain.repositories.ProjectRepository
import kotlinx.coroutines.flow.Flow

class RemoteProjectRepository(
    private val api: ProjectApi
): ProjectRepository {

    override fun getAll(): Flow<List<Project>> {
        TODO("Not yet implemented")
    }

    override fun getProjectWithBars(id: Long): Flow<ProjectWithBars> {
        TODO("Not yet implemented")
    }

    override suspend fun update(updatedProject: Project) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteAll() {
        TODO("Not yet implemented")
    }

    override suspend fun delete(project: Project) {
        TODO("Not yet implemented")
    }

    override suspend fun create(project: Project) {
        TODO("Not yet implemented")
    }
}