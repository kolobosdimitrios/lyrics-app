package com.comp.lyricsapp.domain.usecases

import com.comp.lyricsapp.data.repo.ProjectRepositoryImpl
import com.comp.lyricsapp.domain.entities.Project
import org.junit.Assert.assertEquals
import org.junit.Test

class DeleteProjectUseCaseTest {
    @Test
    fun `DeleteProjectUseCase calls delete on the repository`() {
        val local = FakeProjectRepository()
        val remote = FakeProjectRepository()
        val repository = ProjectRepositoryImpl(local, remote)
        val useCase = DeleteProjectUseCase(repository)

        val project = Project(3L, "delete", "time")

        useCase(project)

        assertEquals(1, local.deletedProjects.size)
        assertEquals(project, local.deletedProjects.first())
    }
}
