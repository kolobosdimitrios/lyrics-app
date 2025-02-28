package com.comp.lyricsapp.data.mapper

import com.comp.lyricsapp.data.model.ProjectDto
import com.comp.lyricsapp.domain.entities.Project

fun ProjectDto.toEntity(): Project {
    return Project(this.id, this.title)
}

fun Project.toDto(): ProjectDto{
    return ProjectDto(this.id, this.title)
}