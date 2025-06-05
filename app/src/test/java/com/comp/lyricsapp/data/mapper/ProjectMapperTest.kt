package com.comp.lyricsapp.data.mapper

import com.comp.lyricsapp.data.model.BarDto
import com.comp.lyricsapp.data.model.ProjectDto
import com.comp.lyricsapp.data.model.relations.ProjectWithBarsRelationEntity
import com.comp.lyricsapp.domain.entities.Bar
import com.comp.lyricsapp.domain.entities.Project
import com.comp.lyricsapp.domain.entities.ProjectWithBars
import org.junit.Assert.assertEquals
import org.junit.Test

class ProjectMapperTest {
    @Test
    fun `ProjectDto converts to domain entity and back`() {
        val dto = ProjectDto(id = 1L, title = "Demo", timestamp = "ts")
        val entity = dto.toEntity()
        assertEquals(Project(1L, "Demo", "ts"), entity)

        val back = entity.toDto()
        assertEquals(dto, back)
    }

    @Test
    fun `ProjectWithBarsRelationEntity converts to domain and back`() {
        val relation = ProjectWithBarsRelationEntity(
            projectDto = ProjectDto(2L, "Title", "now"),
            bars = listOf(BarDto(id = 5L, projectId = 2L))
        )

        val domain = relation.toDomainEntity()
        assertEquals(
            ProjectWithBars(
                project = Project(2L, "Title", "now"),
                bars = listOf(Bar(id = 5L, projectId = 2L))
            ),
            domain
        )

        val back = domain.toRelationEntity()
        assertEquals(relation, back)
    }
}
