package com.comp.lyricsapp.domain.usecases

import com.comp.lyricsapp.data.repo.ProjectRepositoryImpl
import com.comp.lyricsapp.domain.entities.Project
import kotlinx.coroutines.flow.flowOf
import org.junit.Assert.assertEquals
import org.junit.Assert.assertSame
import org.junit.Test

class GetAllProjectsUseCaseTest {
    @Test
    fun `GetAllProjectsUseCase returns repository flow`() {
        val expectedFlow = flowOf(listOf(Project(4L, "title", "ts")))
        val local = FakeProjectRepository(allProjectsFlow = expectedFlow)
        val remote = FakeProjectRepository()
        val repository = ProjectRepositoryImpl(local, remote)
        val useCase = GetAllProjectsUseCase(repository)

        val result = useCase(Unit, async = false)

        assertSame(expectedFlow, result)
        assertEquals(1, local.getAllCalls)
    }
}
