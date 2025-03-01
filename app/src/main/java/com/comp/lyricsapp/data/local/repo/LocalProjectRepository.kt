package com.comp.lyricsapp.data.local.repo

import android.util.Log
import com.comp.lyricsapp.data.local.dao.ProjectDAO
import com.comp.lyricsapp.data.mapper.toDto
import com.comp.lyricsapp.data.mapper.toEntity
import com.comp.lyricsapp.domain.entities.Project
import com.comp.lyricsapp.domain.repositories.ProjectRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map

class LocalProjectRepository(private val projectDao: ProjectDAO): ProjectRepository {

    override fun getAll(): Flow<List<Project>> {
        return projectDao.getAllProjects()
            .catch { error -> error.message?.let { Log.e("LocalProjectRepository", it) } }
            .map { it.map { dto -> dto.toEntity() } }
    }

    override suspend fun get(id: Long): Flow<Project?> {
        return projectDao.getProject(id)
            .catch { error -> error.message?.let { Log.e("LocalProjectRepository", it) } }
            .map { it?.toEntity() }
    }

    override suspend fun update(updatedProject: Project) {
        projectDao.updateProject(updatedProject.toDto())
    }

    override suspend  fun deleteAll() {
        projectDao.deleteAllProjects()
    }

    override suspend fun delete(project: Project) {
        projectDao.deleteProject(project.toDto())
    }

    override suspend fun create(project: Project) {
        projectDao.saveProject(project.toDto())
    }
}