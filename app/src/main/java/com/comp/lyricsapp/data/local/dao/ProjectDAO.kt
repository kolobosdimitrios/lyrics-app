package com.comp.lyricsapp.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.comp.lyricsapp.data.model.ProjectDto
import com.comp.lyricsapp.data.model.relations.ProjectWithBarsRelationEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ProjectDAO {

    @Query("SELECT * FROM project_table")
    fun getAllProjects() : Flow<List<ProjectDto>>

    @Query("SELECT * FROM project_table WHERE id = :projectId")
    fun getProject(projectId: Long) : Flow<ProjectDto?>

    @Insert
    suspend fun saveProject(project: ProjectDto)

    @Update
    suspend fun updateProject(updatedProjectDto: ProjectDto)

    @Query("DELETE FROM project_table")
    suspend fun deleteAllProjects()

    @Delete
    suspend fun deleteProject(project: ProjectDto)

    @Transaction
    @Query("SELECT * FROM BARS WHERE projectId = :projectId")
    fun getProjectWithBars(projectId: Long): Flow<ProjectWithBarsRelationEntity>
}