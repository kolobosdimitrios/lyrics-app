package com.comp.lyricsapp.domain.usecases

import com.comp.lyricsapp.data.repo.BarRepositoryImpl
import com.comp.lyricsapp.domain.entities.Bar
import com.comp.lyricsapp.domain.entities.Line
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking

data class ProjectBarIds(val projectId: Long, val barId: Long)

abstract sealed class BarUseCases<I, O>(protected val repository: BarRepositoryImpl){
    protected abstract suspend fun invokeSuspend(input: I): O
    protected abstract fun invokeSync(input: I): O

    operator fun invoke(input: I, async: Boolean = true): O {
        return if(async) runBlocking { invokeSuspend(input) } else invokeSync(input)
    }
}

class CreateBar(repository: BarRepositoryImpl): BarUseCases<Bar, Unit>(repository){
    override suspend fun invokeSuspend(input: Bar) {
        repository.createBar(input)
    }

    override fun invokeSync(input: Bar) {
        throw IllegalStateException("Creation is a suspend operation!")
    }

}

class UpdateBar(repository: BarRepositoryImpl): BarUseCases<Bar, Unit>(repository){
    override suspend fun invokeSuspend(input: Bar) {
        repository.updateBar(input)
    }

    override fun invokeSync(input: Bar) {
        throw IllegalStateException("Update is a suspend operation!")
    }


}

class DeleteProjectBar(repository: BarRepositoryImpl): BarUseCases<ProjectBarIds, Unit>(repository){

    override suspend fun invokeSuspend(input: ProjectBarIds) {
        repository.deleteProjectBars(
            projectId = input.projectId,
            projectBarIds = listOf(input.barId)
        )
    }

    override fun invokeSync(input: ProjectBarIds) {
        throw IllegalStateException("Delete is a suspend operation!")
    }

}

class DeleteProjectBars(repository: BarRepositoryImpl): BarUseCases<Long, Unit>(repository){
    override suspend fun invokeSuspend(input: Long) {
        repository.deleteAllProjectBars(input)
    }

    override fun invokeSync(input: Long) {
        throw IllegalStateException("Delete is a suspend operation!")
    }


}

class GetBarLinesUseCase(repository: BarRepositoryImpl): BarUseCases<Long, Flow<List<Line>>>(repository){
    override suspend fun invokeSuspend(input: Long): Flow<List<Line>> {
        throw IllegalStateException("Delete is not a suspend operation!")
    }

    override fun invokeSync(input: Long): Flow<List<Line>> {
        return repository.getBarWithLines(input).map { it.barLines }
    }
}