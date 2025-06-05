package com.comp.lyricsapp.domain.usecases

import com.comp.lyricsapp.domain.entities.Project
import com.comp.lyricsapp.domain.entities.ProjectWithBars
import com.comp.lyricsapp.domain.repositories.ProjectRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flowOf

/**
 * Simple fake implementation of [ProjectRepository] used for unit testing.
 * It records calls to each method so that tests can assert interactions.
 */
class FakeProjectRepository(
    var allProjectsFlow: Flow<List<Project>> = flowOf(emptyList()),
    var projectWithBarsFlow: Flow<ProjectWithBars> = emptyFlow()
) : ProjectRepository {

    val createdProjects = mutableListOf<Project>()
    val updatedProjects = mutableListOf<Project>()
    val deletedProjects = mutableListOf<Project>()
    var deleteAllCalls = 0
    var getAllCalls = 0
    val getProjectWithBarsCalls = mutableListOf<Long>()

    override fun getAll(): Flow<List<Project>> {
        getAllCalls++
        return allProjectsFlow
    }

    override fun getProjectWithBars(id: Long): Flow<ProjectWithBars> {
        getProjectWithBarsCalls.add(id)
        return projectWithBarsFlow
    }

    override suspend fun update(updatedProject: Project) {
        updatedProjects.add(updatedProject)
    }

    override suspend fun deleteAll() {
        deleteAllCalls++
    }

    override suspend fun delete(project: Project) {
        deletedProjects.add(project)
    }

    override suspend fun create(project: Project) {
        createdProjects.add(project)
    }
}
