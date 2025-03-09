package com.comp.lyricsapp.data.model.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.comp.lyricsapp.data.model.BarDto
import com.comp.lyricsapp.data.model.LineDto

data class BarWithLinesRelationEntity(
    @Embedded val bar: BarDto,
    @Relation(
        parentColumn = "id",
        entityColumn = "barId"
    )
    val lines: List<LineDto>
) {
}