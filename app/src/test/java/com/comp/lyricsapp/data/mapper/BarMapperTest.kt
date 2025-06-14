package com.comp.lyricsapp.data.mapper

import com.comp.lyricsapp.data.model.BarDto
import com.comp.lyricsapp.data.model.LineDto
import com.comp.lyricsapp.data.model.relations.BarWithLinesRelationEntity
import com.comp.lyricsapp.domain.entities.Bar
import com.comp.lyricsapp.domain.entities.BarWithLines
import com.comp.lyricsapp.domain.entities.Line
import org.junit.Assert.assertEquals
import org.junit.Test

class BarMapperTest {
    @Test
    fun `BarDto converts to domain entity and back`() {
        val dto = BarDto(id = 3L, projectId = 7L)
        val entity = dto.toEntity()
        assertEquals(Bar(3L, 7L), entity)

        val back = entity.toDto()
        assertEquals(dto, back)
    }

    @Test
    fun `BarWithLinesRelationEntity converts to domain and back`() {
        val relation = BarWithLinesRelationEntity(
            bar = BarDto(id = 4L, projectId = 1L),
            barLines = listOf(
                LineDto(id = 1L, barId = 4L, line = "line", timestamp = "t")
            )
        )

        val domain = relation.toDomainEntity()
        assertEquals(
            BarWithLines(
                bar = Bar(id = 4L, projectId = 1L),
                barLines = listOf(Line(id = 1L, barId = 4L, line = "line", timestamp = "t"))
            ),
            domain
        )

        val back = domain.toRelationEntity()
        assertEquals(relation, back)
    }
}
