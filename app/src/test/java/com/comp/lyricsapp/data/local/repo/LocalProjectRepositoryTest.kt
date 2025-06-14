package com.comp.lyricsapp.data.local.repo

import com.comp.lyricsapp.data.local.dao.ProjectDAO
import com.comp.lyricsapp.data.model.ProjectDto
import com.comp.lyricsapp.domain.entities.Project
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

class LocalProjectRepositoryTest {
    private class FakeProjectDao(initial: List<ProjectDto>) : ProjectDAO {
        private val projectsFlow = MutableStateFlow(initial)

        override fun getAllProjects(): Flow<List<ProjectDto>> = projectsFlow

        override fun getProject(projectId: Long): Flow<ProjectDto?> =
            projectsFlow.map { list -> list.find { it.id == projectId } }

        override suspend fun saveProject(project: ProjectDto) {
            projectsFlow.value = projectsFlow.value + project
        }

        override suspend fun updateProject(updatedProjectDto: ProjectDto) {
            projectsFlow.value = projectsFlow.value.map {
                if (it.id == updatedProjectDto.id) updatedProjectDto else it
            }
        }

        override suspend fun deleteAllProjects() {
            projectsFlow.value = emptyList()
        }

        override suspend fun deleteProject(project: ProjectDto) {
            projectsFlow.value = projectsFlow.value.filterNot { it.id == project.id }
        }

        override fun getProjectWithBars(projectId: Long): Flow<com.comp.lyricsapp.data.model.relations.ProjectWithBarsRelationEntity> {
            throw UnsupportedOperationException("Not needed")
        }
    }

    @Test
    fun `getAll maps dto list to domain`() = runBlocking {
        val dtoList = listOf(
            ProjectDto(1L, "one", "t1"),
            ProjectDto(2L, "two", "t2")
        )
        val repository = LocalProjectRepository(FakeProjectDao(dtoList))

        val result = repository.getAll().first()

        assertEquals(
            listOf(
                Project(1L, "one", "t1"),
                Project(2L, "two", "t2")
            ),
            result
        )
    }
}
