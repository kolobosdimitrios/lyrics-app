package com.comp.lyricsapp.domain.repositories

import com.comp.lyricsapp.domain.entities.Project
import kotlinx.coroutines.flow.Flow

interface ProjectRepository {

    fun getAll() : Flow<List<Project>>

    suspend fun get(id: Long) : Project

    suspend fun deleteAll()

    suspend fun delete(project: Project)

    suspend fun create(project: Project)
}