package com.comp.lyricsapp.data.repo

import com.comp.lyricsapp.data.local.repo.LocalProjectRepository
import com.comp.lyricsapp.data.mapper.toDomainEntity
import com.comp.lyricsapp.data.model.relations.ProjectWithBarsRelationEntity
import com.comp.lyricsapp.data.remote.repo.RemoteProjectRepository
import com.comp.lyricsapp.domain.entities.Project
import com.comp.lyricsapp.domain.entities.ProjectWithBars
import com.comp.lyricsapp.domain.repositories.ProjectRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ProjectRepositoryImpl(
    private val localProjectRepository: LocalProjectRepository,
    private val remoteProjectRepository: RemoteProjectRepository
): ProjectRepository {

    override fun getAll(): Flow<List<Project>> {

        return localProjectRepository.getAll()
    }

    override fun getProjectWithBars(id: Long): Flow<ProjectWithBars> {
        return localProjectRepository.getProjectWithBars(id)
    }

    override suspend fun update(updatedProject: Project) {
        localProjectRepository.update(updatedProject)
    }

    override suspend fun deleteAll() {
        localProjectRepository.deleteAll()
    }

    override suspend fun delete(project: Project) {
        localProjectRepository.delete(project)
    }

    override suspend fun create(project: Project) {
        localProjectRepository.create(project)
    }
}