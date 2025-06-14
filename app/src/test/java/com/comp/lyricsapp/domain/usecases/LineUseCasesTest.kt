package com.comp.lyricsapp.domain.usecases

import com.comp.lyricsapp.domain.entities.BarWithLines
import com.comp.lyricsapp.domain.entities.Bar
import com.comp.lyricsapp.domain.entities.Line
import org.junit.Assert.assertEquals
import org.junit.Test

class LineUseCasesTest {
    private val repository = LineRepositoryImplFake()
    private val createLine = CreateLineUseCase(repository)
    private val updateLine = UpdateLineUseCase(repository)
    private val deleteBarLine = DeleteBarLineUseCase(repository)

    @Test
    fun `CreateLineUseCase delegates to repository`() {
        val line = Line(1L, 1L, "", "")
        createLine(line)
        assertEquals(line, repository.createdLine)
    }

    @Test
    fun `UpdateLineUseCase delegates to repository`() {
        val line = Line(2L, 1L, "", "")
        updateLine(line)
        assertEquals(line, repository.updatedLine)
    }

    @Test
    fun `DeleteBarLineUseCase delegates to repository`() {
        val barWithLines = BarWithLines(Bar(1L,1L), listOf(Line(3L,1L,"","")))
        deleteBarLine(barWithLines)
        assertEquals(barWithLines.barLines, repository.deletedLines)
    }
}
