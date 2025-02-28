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
        TODO("Not yet implemented")
    }

    override suspend fun get(id: Long): Project {
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