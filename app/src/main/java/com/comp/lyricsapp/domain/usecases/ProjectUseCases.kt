package com.comp.lyricsapp.domain.usecases

import com.comp.lyricsapp.data.model.relations.ProjectWithBarsRelationEntity
import com.comp.lyricsapp.data.repo.ProjectRepositoryImpl
import com.comp.lyricsapp.domain.entities.Project
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.runBlocking

abstract sealed class ProjectUseCase<I,O>(protected val repository: ProjectRepositoryImpl){
    protected abstract suspend fun invokeSuspend(input: I): O
    protected abstract fun invokeSync(input: I): O

    operator fun invoke(input: I, async: Boolean = true): O {
        return if(async) runBlocking { invokeSuspend(input) } else invokeSync(input)
    }
}

class UpdateProjectUSeCase(repository: ProjectRepositoryImpl): ProjectUseCase<Project, Unit>(repository){
    override suspend fun invokeSuspend(input: Project) {
        repository.update(input)
    }

    override fun invokeSync(input: Project) {
        throw IllegalStateException("Update is a suspended operation")
    }


}

class CreateProjectUseCase(repository: ProjectRepositoryImpl): ProjectUseCase<Project, Unit>(repository){

    override suspend fun invokeSuspend(input: Project) {
        repository.create(input)
    }

    override fun invokeSync(input: Project) {
        throw IllegalStateException("Update is a suspended operation")
    }

}

class DeleteProjectUseCase(repository: ProjectRepositoryImpl): ProjectUseCase<Project, Unit>(repository) {


    override suspend fun invokeSuspend(input: Project) {
        repository.delete(input)
    }

    override fun invokeSync(input: Project) {
        throw IllegalStateException("Update is a suspended operation")
    }

}

class GetAllProjectsUseCase(repository: ProjectRepositoryImpl): ProjectUseCase<Unit, Flow<List<Project>>>(repository){


    override suspend fun invokeSuspend(input: Unit): Flow<List<Project>> {
        throw IllegalStateException("Get is not a suspended operation")
    }

    override fun invokeSync(input: Unit): Flow<List<Project>> {
        return repository.getAll()
    }

}

class GetProjectWithBars(repository: ProjectRepositoryImpl): ProjectUseCase<Long, Flow<ProjectWithBarsRelationEntity>>(repository){

    override suspend fun invokeSuspend(input: Long): Flow<ProjectWithBarsRelationEntity> {
        throw IllegalStateException("Get is not a suspended operation")
    }

    override fun invokeSync(input: Long): Flow<ProjectWithBarsRelationEntity> {
        return repository.getProjectWithBars(input)
    }

}