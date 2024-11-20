package com.comp.lyricsapp.data.repo

import com.comp.lyricsapp.data.local.ProjectDAO
import com.comp.lyricsapp.data.model.ProjectEntity
import kotlinx.coroutines.flow.Flow

class ProjectRepositoryImpl(private val projectDao: ProjectDAO): ProjectRepository {

    override fun getAll(): Flow<List<ProjectEntity>> {
        return projectDao.getAllProjects()
    }

    override suspend fun get(id: Long): ProjectEntity {
        return projectDao.getProject(id)
    }

    override suspend  fun deleteAll() {
        projectDao.deleteAllProjects()
    }

    override suspend fun delete(projectEntity: ProjectEntity) {
        projectDao.deleteProject(projectEntity)
    }

    override suspend fun create(projectEntity: ProjectEntity) {
        projectDao.saveProject(projectEntity)
    }
}