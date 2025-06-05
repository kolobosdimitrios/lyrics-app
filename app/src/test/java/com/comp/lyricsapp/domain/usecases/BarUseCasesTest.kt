package com.comp.lyricsapp.domain.usecases

import com.comp.lyricsapp.domain.entities.Bar
import com.comp.lyricsapp.domain.entities.Line
import kotlinx.coroutines.flow.first
import org.junit.Assert.assertEquals
import org.junit.Test

class BarUseCasesTest {
    private val repository = BarRepositoryImplFake()
    private val createBar = CreateBar(repository)
    private val updateBar = UpdateBar(repository)
    private val deleteProjectBar = DeleteProjectBar(repository)
    private val deleteProjectBars = DeleteProjectBars(repository)
    private val getBarLinesUseCase = GetBarLinesUseCase(repository)

    @Test
    fun `CreateBar delegates to repository`() {
        val bar = Bar(1L, 2L)
        createBar(bar)
        assertEquals(bar, repository.createdBar)
    }

    @Test
    fun `UpdateBar delegates to repository`() {
        val bar = Bar(2L, 1L)
        updateBar(bar)
        assertEquals(bar, repository.updatedBar)
    }

    @Test
    fun `DeleteProjectBar delegates to repository`() {
        val projectId = 1L
        val barId = 5L
        deleteProjectBar(ProjectBarIds(projectId, barId))
        assertEquals(projectId to listOf(barId), repository.deleteProjectBarsArgs)
    }

    @Test
    fun `DeleteProjectBars delegates to repository`() {
        val projectId = 3L
        deleteProjectBars(projectId)
        assertEquals(projectId, repository.deleteAllProjectBarsArg)
    }

    @Test
    fun `GetBarLinesUseCase delegates to repository`() {
        val id = 7L
        val lines = getBarLinesUseCase(id, async = false).first()
        assertEquals(id, repository.getBarLinesArg)
        assertEquals(emptyList<Line>(), lines)
    }
}
