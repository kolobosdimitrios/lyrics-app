package com.comp.lyricsapp.domain.usecases

import com.comp.lyricsapp.data.repo.ProjectRepositoryImpl
import com.comp.lyricsapp.domain.entities.Project
import org.junit.Assert.assertEquals
import org.junit.Test

class CreateProjectUseCaseTest {
    @Test
    fun `CreateProjectUseCase calls create on the repository`() {
        val local = FakeProjectRepository()
        val remote = FakeProjectRepository()
        val repository = ProjectRepositoryImpl(local, remote)
        val useCase = CreateProjectUseCase(repository)

        val project = Project(1L, "test", "now")

        useCase(project)

        assertEquals(1, local.createdProjects.size)
        assertEquals(project, local.createdProjects.first())
    }
}
