package com.comp.lyricsapp.data.mapper

import com.comp.lyricsapp.data.model.LineDto
import com.comp.lyricsapp.domain.entities.Line

fun LineDto.toEntity(): Line {
    return Line(
        id = this.id,
        barId = this.barId,
        timestamp = this.timestamp,
        line = this.line
    )
}

fun Line.toDto(): LineDto {
    return LineDto(
        id = this.id,
        barId = this.barId,
        timestamp = this.timestamp,
        line = this.line
    )
}
