package com.comp.lyricsapp.data.repo

import com.comp.lyricsapp.data.local.repo.LocalProjectRepository
import com.comp.lyricsapp.data.remote.repo.RemoteProjectRepository
import com.comp.lyricsapp.domain.entities.Project
import com.comp.lyricsapp.domain.repositories.ProjectRepository
import kotlinx.coroutines.flow.Flow

class ProjectRepositoryImpl(
    private val localProjectRepository: LocalProjectRepository,
    private val remoteProjectRepository: RemoteProjectRepository
): ProjectRepository {

    override fun getAll(): Flow<List<Project>> {

        return localProjectRepository.getAll()
    }

    override suspend fun get(id: Long): Flow<Project?> {
        return localProjectRepository.get(id)
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