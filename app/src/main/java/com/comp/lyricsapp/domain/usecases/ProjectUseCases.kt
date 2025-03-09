package com.comp.lyricsapp.domain.usecases

import com.comp.lyricsapp.data.local.dao.ProjectDAO
import com.comp.lyricsapp.data.model.relations.ProjectWithBarsRelationEntity
import com.comp.lyricsapp.domain.entities.Project
import kotlinx.coroutines.flow.Flow

abstract sealed class ProjectUseCase<I,O>(protected val projectDao: ProjectDAO){
    abstract operator fun invoke(input: I): O
}

class UpdateProjectUSeCase(projectDao: ProjectDAO): ProjectUseCase<Project, Unit>(projectDao){
    override fun invoke(updatedProject: Project) {
        TODO("Not yet implemented")
    }

}

class CreateProjectUseCase(projectDao: ProjectDAO): ProjectUseCase<Project, Unit>(projectDao){
    override fun invoke(newProject: Project) {
        TODO("Not yet implemented")
    }

}

class DeleteProjectUseCase(projectDao: ProjectDAO): ProjectUseCase<Project, Unit>(projectDao) {
    override fun invoke(deletedProject: Project) {
        TODO("Not yet implemented")
    }

}

class GetAllProjectsUseCase(projectDao: ProjectDAO): ProjectUseCase<Unit, Flow<List<Project>>>(projectDao){
    override fun invoke(input: Unit): Flow<List<Project>> {
        TODO("Not yet implemented")
    }

}

class GetProjectWithBars(projectDao: ProjectDAO): ProjectUseCase<Long, Flow<ProjectWithBarsRelationEntity>>(projectDao){
    override fun invoke(projectId: Long): Flow<ProjectWithBarsRelationEntity> {
        TODO("Not yet implemented")
    }

}