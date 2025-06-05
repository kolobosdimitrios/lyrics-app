package com.comp.lyricsapp.domain.usecases

import com.comp.lyricsapp.data.repo.ProjectRepositoryImpl
import com.comp.lyricsapp.domain.entities.Project
import org.junit.Assert.assertEquals
import org.junit.Test

class UpdateProjectUseCaseTest {
    @Test
    fun `UpdateProjectUseCase calls update on the repository`() {
        val local = FakeProjectRepository()
        val remote = FakeProjectRepository()
        val repository = ProjectRepositoryImpl(local, remote)
        val useCase = UpdateProjectUseCase(repository)

        val project = Project(2L, "update", "time")

        useCase(project)

        assertEquals(1, local.updatedProjects.size)
        assertEquals(project, local.updatedProjects.first())
    }
}
