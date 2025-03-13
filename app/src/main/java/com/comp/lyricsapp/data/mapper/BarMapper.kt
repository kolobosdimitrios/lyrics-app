package com.comp.lyricsapp.data.mapper

import com.comp.lyricsapp.data.model.BarDto
import com.comp.lyricsapp.data.model.relations.BarWithLinesRelationEntity
import com.comp.lyricsapp.domain.entities.Bar
import com.comp.lyricsapp.domain.entities.BarWithLines

fun BarDto.toEntity() : Bar {

    return Bar(id = this.id, projectId = this.projectId)
}

fun Bar.toDto() : BarDto{

    return BarDto(id = this.id, projectId = this.projectId)
}

fun BarWithLinesRelationEntity.toDomainEntity() : BarWithLines{
    return BarWithLines(
        bar = this.bar.toEntity(),
        barLines = this.barLines.map { it.toEntity() }
    )
}

fun BarWithLines.toRelationEntity() : BarWithLinesRelationEntity{
    return BarWithLinesRelationEntity(
        bar = this.bar.toDto(),
        barLines = this.barLines.map { it.toDto() }
    )
}
