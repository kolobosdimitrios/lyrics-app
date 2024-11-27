package com.comp.lyricsapp.data.repo

import com.comp.lyricsapp.data.local.dao.ProjectDAO
import com.comp.lyricsapp.domain.entities.Project
import com.comp.lyricsapp.domain.repositories.ProjectRepository
import kotlinx.coroutines.flow.Flow

class ProjectRepositoryImpl(private val projectDao: ProjectDAO): ProjectRepository {

    override fun getAll(): Flow<List<Project>> {
        return projectDao.getAllProjects()
    }

    override suspend fun get(id: Long): Project {
        return projectDao.getProject(id)
    }

    override suspend  fun deleteAll() {
        projectDao.deleteAllProjects()
    }

    override suspend fun delete(project: Project) {
        projectDao.deleteProject(project)
    }

    override suspend fun create(project: Project) {
        projectDao.saveProject(project)
    }
}