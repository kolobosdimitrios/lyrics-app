package com.comp.lyricsapp.data.mapper

import com.comp.lyricsapp.data.model.LineDto
import com.comp.lyricsapp.domain.entities.Line
import org.junit.Assert.assertEquals
import org.junit.Test

class LineMapperTest {
    @Test
    fun `LineDto converts to domain entity and back`() {
        val dto = LineDto(id = 10L, barId = 2L, line = "hi", timestamp = "now")
        val entity = dto.toEntity()
        assertEquals(Line(10L, 2L, "hi", "now"), entity)

        val back = entity.toDto()
        assertEquals(dto, back)
    }
}
