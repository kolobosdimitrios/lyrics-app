package com.comp.lyricsapp.domain.usecases

import com.comp.lyricsapp.data.local.dao.BarDao
import com.comp.lyricsapp.domain.entities.Bar
import com.comp.lyricsapp.domain.entities.Line
import kotlinx.coroutines.flow.Flow

data class ProjectBarIds(val projectId: Long, val barId: Long)

abstract sealed class BarUseCases<I, O>(protected val barDao: BarDao){
    abstract operator fun invoke(input: I): O
}

class CreateBar(barDao: BarDao): BarUseCases<Bar, Unit>(barDao){
    override fun invoke(newBar: Bar) {
        TODO("Not yet implemented")
    }
}

class UpdateBar(barDao: BarDao): BarUseCases<Bar, Unit>(barDao){
    override fun invoke(updatedBar: Bar) {
        TODO("Not yet implemented")
    }

}

class DeleteBar(barDao: BarDao): BarUseCases<Bar, Unit>(barDao){
    override fun invoke(deletedBar: Bar) {
        TODO("Not yet implemented")
    }

}

class DeleteProjectBar(barDao: BarDao): BarUseCases<ProjectBarIds, Unit>(barDao){
    override fun invoke(projectBarIds: ProjectBarIds) {
        TODO("Not yet implemented")
    }

}

class DeleteProjectBars(barDao: BarDao): BarUseCases<Long, Unit>(barDao){
    override fun invoke(projectId: Long) {
        TODO("Not yet implemented")
    }

}

class GetBarLinesUseCase(barDao: BarDao): BarUseCases<Long, Flow<List<Line>>>(barDao){
    override fun invoke(barId: Long): Flow<List<Line>> {
        TODO("Not yet implemented")
    }

}