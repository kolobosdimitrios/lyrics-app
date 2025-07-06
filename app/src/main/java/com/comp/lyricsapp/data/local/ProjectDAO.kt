package com.comp.lyricsapp.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.comp.lyricsapp.data.model.ProjectEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ProjectDAO {

    @Query("SELECT * FROM project_table")
    fun getAllProjects() : Flow<List<ProjectEntity>>

    @Query("SELECT * FROM project_table WHERE id = :projectId")
    suspend fun getProject(projectId: Long) : ProjectEntity

    @Insert
    suspend fun saveProject(projectEntity: ProjectEntity)

    @Query("DELETE FROM project_table")
    suspend fun deleteAllProjects()

    @Delete
    suspend fun deleteProject(projectEntity: ProjectEntity)
}