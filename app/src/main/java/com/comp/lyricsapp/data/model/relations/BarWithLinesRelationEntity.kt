package com.comp.lyricsapp.data.model.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.comp.lyricsapp.data.model.BarDto
import com.comp.lyricsapp.data.model.LineDto

data class BarWithLinesRelationEntity(
    @Embedded val bar: BarDto,
    @Relation(
        parentColumn = "id",
        entityColumn = "bar_id" // ✅ FIXED: Use actual property name, NOT column name
    )
    val barLines: List<LineDto>
)
