package com.comp.lyricsapp.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.comp.lyricsapp.data.model.ProjectDto
import com.comp.lyricsapp.data.model.relations.ProjectWithBarsRelationEntity
import com.comp.lyricsapp.domain.entities.ProjectWithBars
import kotlinx.coroutines.flow.Flow

@Dao
interface ProjectDAO {

    @Query("SELECT * FROM projects")
    fun getAllProjects() : Flow<List<ProjectDto>>

    @Query("SELECT * FROM projects WHERE id = :projectId")
    fun getProject(projectId: Long) : Flow<ProjectDto?>

    @Insert
    suspend fun saveProject(project: ProjectDto)

    @Update
    suspend fun updateProject(updatedProjectDto: ProjectDto)

    @Query("DELETE FROM projects")
    suspend fun deleteAllProjects()

    @Delete
    suspend fun deleteProject(project: ProjectDto)

    @Transaction
    @Query("SELECT * FROM BARS, PROJECTS WHERE project_id = :projectId")
    fun getProjectWithBars(projectId: Long): Flow<ProjectWithBarsRelationEntity>
}