package com.comp.lyricsapp.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.comp.lyricsapp.domain.entities.Project
import kotlinx.coroutines.flow.Flow

@Dao
interface ProjectDAO {

    @Query("SELECT * FROM project_table")
    fun getAllProjects() : Flow<List<Project>>

    @Query("SELECT * FROM project_table WHERE id = :projectId")
    suspend fun getProject(projectId: Long) : Project

    @Insert
    suspend fun saveProject(project: Project)

    @Query("DELETE FROM project_table")
    suspend fun deleteAllProjects()

    @Delete
    suspend fun deleteProject(project: Project)
}