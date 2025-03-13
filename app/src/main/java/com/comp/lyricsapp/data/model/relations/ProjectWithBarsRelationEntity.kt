package com.comp.lyricsapp.data.model.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.comp.lyricsapp.data.model.BarDto
import com.comp.lyricsapp.data.model.ProjectDto

data class ProjectWithBarsRelationEntity(
    @Embedded val projectDto: ProjectDto,
    @Relation(
        parentColumn = "id",
        entityColumn = "project_id" // ✅ FIXED: Use actual property name, NOT column name
    )
    val bars: List<BarDto>
)
