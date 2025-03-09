package com.comp.lyricsapp.data.mapper

import com.comp.lyricsapp.data.model.ProjectDto
import com.comp.lyricsapp.data.model.relations.ProjectWithBarsRelationEntity
import com.comp.lyricsapp.domain.entities.Project
import com.comp.lyricsapp.domain.entities.ProjectWithBars

fun ProjectDto.toEntity(): Project {
    return Project(this.id, this.title, this.timestamp)
}

fun Project.toDto(): ProjectDto{
    return ProjectDto(this.id, this.title, this.timeStamp)
}

fun ProjectWithBarsRelationEntity.toDomainEntity() : ProjectWithBars{
    return ProjectWithBars(
        project = this.projectDto.toEntity(),
        bars = this.bars.map { it.toEntity() }
    )
}

fun ProjectWithBars.toRelationEntity() : ProjectWithBarsRelationEntity{
    return ProjectWithBarsRelationEntity(
        projectDto = this.project.toDto(),
        bars = this.bars.map { it.toDto() }
    )
}