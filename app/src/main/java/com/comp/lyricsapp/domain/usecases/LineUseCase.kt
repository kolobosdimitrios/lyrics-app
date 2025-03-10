package com.comp.lyricsapp.domain.usecases

import com.comp.lyricsapp.data.repo.LineRepositoryImpl
import com.comp.lyricsapp.domain.entities.Line
import kotlinx.coroutines.runBlocking

data class BarLineIds(val barId: Long, val lineId: Long )

sealed abstract class LineUseCase<I,O>(protected val repository: LineRepositoryImpl) {
    protected abstract suspend fun invokeSuspend(input: I): O
    protected abstract fun invokeSync(input: I): O

    operator fun invoke(input: I, async: Boolean = true): O {
        return if(async) runBlocking { invokeSuspend(input) } else invokeSync(input)
    }
}


class CreateLineUseCase(repository: LineRepositoryImpl): LineUseCase<Line, Unit>(repository) {

    override suspend fun invokeSuspend(input: Line) {
        repository.createLine(input)
    }

    override fun invokeSync(input: Line) {
        throw IllegalStateException("Creation is a suspended operation.")
    }

}

class UpdateLineUseCase(repository: LineRepositoryImpl): LineUseCase<Line, Unit>(repository) {

    override suspend fun invokeSuspend(input: Line) {
        repository.updateLine(input)
    }

    override fun invokeSync(input: Line) {
        throw IllegalStateException("Update is a suspended operation.")
    }
}


class DeleteBarLineUseCase(repository: LineRepositoryImpl): LineUseCase<BarLineIds ,Unit>(repository){
    override suspend fun invokeSuspend(input: BarLineIds) {
        repository.deleteBarLines(
            input.barId,
            listOf(input.lineId)
        )
    }

    override fun invokeSync(input: BarLineIds) {
        throw IllegalStateException("Deletion is a suspended operation.")
    }


}